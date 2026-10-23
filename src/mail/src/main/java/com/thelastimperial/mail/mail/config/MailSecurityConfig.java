package com.thelastimperial.mail.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MailSecurityConfig {

    @Bean
    public SecurityFilterChain mailSecurityFilterChain( HttpSecurity http ) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/mails/**")
                    .hasAnyAuthority("SCOPE_USER")
                .requestMatchers(HttpMethod.GET, "/")
                    .hasAnyAuthority("SCOPE_USER")
                .requestMatchers("/css/mail/**","/js/mail/**").permitAll()
                .requestMatchers("/error").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
