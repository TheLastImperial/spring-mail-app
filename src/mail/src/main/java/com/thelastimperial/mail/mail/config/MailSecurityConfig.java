package com.thelastimperial.mail.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;

@Configuration
public class MailSecurityConfig {
    @Bean
    public SecurityFilterChain mailSecurityFilterChain(
        HttpSecurity http, RememberMeServices rememberMeServices
    ) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/mails/**").hasRole("USER")
            .requestMatchers("/css/**","/js/**","/auth/**").permitAll()
            .requestMatchers("/error").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin( login -> login
            .loginPage("/auth/login")
            .failureUrl("/auth/login?error=true")
            .defaultSuccessUrl("/", true)
            .permitAll()
        )
        .rememberMe(rememberme -> rememberme
            .rememberMeServices(rememberMeServices)
            .rememberMeParameter("remember-me")
        )
        .logout(logout -> logout
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/auth/login")
            .permitAll()
        );
        return http.build();
    }
}
