package com.example.demo.infrastructure.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * カスタムの認証プロバイダクラスです。
 * Spring Securityの AuthenticationProvider インターフェースを実装しています。
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    /**
     * CustomAuthenticationProviderのコンストラクターです。
     *
     * @param userDetailsService ユーザーの詳細情報を提供するサービス
     */
    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 認証を試行します。
     *
     * @param authentication 認証情報
     * @return 認証が成功した場合は認証情報、それ以外の場合は例外がスローされます
     * @throws AuthenticationException 認証に失敗した場合の例外
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // SecurityConfigで作成したPasswordEncoderのBeanを使用する
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Authentication failed");
        }
    }

    /**
     * このプロバイダが指定された認証オブジェクトをサポートするかどうかを判定します。
     *
     * @param authentication 認証オブジェクトのクラス
     * @return サポートする場合はtrue、それ以外の場合はfalse
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
