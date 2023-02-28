package com.tanchee.starcircleplus;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        // security.httpBasic().disable(); // only works for GET
        // http.csrf().disable().authorizeRequests().anyRequest().permitAll(); // works for GET, POST, PUT, DELETE
        
        http.authorizeHttpRequests((authz) -> authz.anyRequest().permitAll())
            .httpBasic();

        return http.build();
                                                                            
    }
}
