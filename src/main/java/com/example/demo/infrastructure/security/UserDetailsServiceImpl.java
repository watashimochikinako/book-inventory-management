package com.example.demo.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

/**
 * Spring SecurityのUserDetailsServiceを実装したサービスクラスです。
 * ユーザー情報の取得とSpring Securityでの認証に使用されます。
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 指定されたメールアドレスを持つユーザーの詳細を取得します。
     *
     * @param email ユーザーのメールアドレス
     * @return UserDetailsオブジェクト
     * @throws UsernameNotFoundException ユーザーが見つからない場合にスローされます
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("指定されたメールアドレスのユーザーが見つかりません: " + email);
        }
    
        // ユーザーの権限をROLE_USERとして設定して返す
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true, true, true, true,
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}