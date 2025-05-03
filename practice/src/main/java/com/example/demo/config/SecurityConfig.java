package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/signup").permitAll() // 管理者登録画面へのアクセスを許可
                .anyRequest().authenticated() // その他のページは認証必須
            )
            .formLogin(form -> form
                .loginPage("/admin/signin") // 独自ログインページを指定
                .permitAll()
            )
            .logout(logout -> logout.permitAll()); // ログアウトの設定

        return http.build();
    }
}