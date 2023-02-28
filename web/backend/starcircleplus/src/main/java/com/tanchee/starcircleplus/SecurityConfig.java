package com.tanchee.starcircleplus;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/", "/home", "/greeting").permitAll()
                    .requestMatchers("/user").access(new WebExpressionAuthorizationManager("hasRole('USER')"))
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/db/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))
                    .anyRequest().denyAll());

        return http.build();
    }
}
