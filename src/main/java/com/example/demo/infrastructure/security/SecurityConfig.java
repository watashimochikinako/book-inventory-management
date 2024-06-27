package com.example.demo.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Securityの設定を行う設定クラスです。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final AuthenticationProvider authenticationProvider;

    /**
     * SecurityConfigのコンストラクターです。
     *
     * @param userDetailsService     ユーザーの詳細情報を取得するためのサービス
     * @param authenticationProvider 認証プロバイダー
     */
    public SecurityConfig(UserDetailsService userDetailsService, AuthenticationProvider authenticationProvider) {
        this.userDetailsService = userDetailsService;
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * パスワードのエンコーダーをBeanとして登録します。
     *
     * @return パスワードのエンコーダー
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Spring Securityのフィルターチェーンを構成します。
     *
     * @param http HttpSecurityオブジェクト
     * @return SecurityFilterChainオブジェクト
     * @throws Exception 構成時の例外
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(
                        "/toLogin",
                        "/login",
                        "/topPage",
                        "/css/**",
                        "/js/**",
                        "/img/**")
                .permitAll().anyRequest().authenticated());

        http.formLogin((login) -> login
                .loginPage("/toLogin")
                .loginProcessingUrl("/login")
                .failureUrl("/toLogin?error=true")
                .defaultSuccessUrl("/topPage", true)
                .usernameParameter("email")
                .passwordParameter("password"));

        http.logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/topPage")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1) // 同一ユーザーで複数のセッションを許可しない場合
                        .expiredUrl("/toLogin?expired=true"));

        http.authenticationProvider(authenticationProvider);
        http.userDetailsService(userDetailsService);

        return http.build();
    }
}
