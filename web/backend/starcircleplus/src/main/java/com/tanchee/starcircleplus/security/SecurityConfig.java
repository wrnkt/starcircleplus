package com.tanchee.starcircleplus.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/", "/home", "/user/**").permitAll().anyRequest()
                    //.requestMatchers("/user").access(new WebExpressionAuthorizationManager("hasRole('USER')"))
                    //.requestMatchers("/admin/**").hasRole("ADMIN")
                    //.requestMatchers("/db/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))
                    //.anyRequest().denyAll()
            )
            //.logout((logout) -> logout.permitAll())
            .httpBasic(withDefaults());

        return http.build();
    }
}
