package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.repository.AdminRepository;

@Configuration
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/signup", "/admin/signin").permitAll() // 管理者登録 & ログイン画面へのアクセスを許可
            .requestMatchers("/contact/**").permitAll() // contactエンドポイントは全て認証不要
            .anyRequest().authenticated() // その他のページは認証必須
        )
        .formLogin(form -> form
            .loginPage("/admin/signin") // 独自ログインページを指定
            .defaultSuccessUrl("/admin/contacts", true) // ログイン成功時のURLを指定 (常にリダイレクト)
            .permitAll()
        )
        .logout(logout -> logout  
            .logoutSuccessUrl("/admin/signin") // ログアウト成功時のURL
            .permitAll()
        );

    return http.build();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService(AdminRepository adminRepository) {
    	
        return email -> adminRepository.findByEmail(email)
            .map(admin -> User.withUsername(admin.getEmail())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build())
            .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + email));
    }
}