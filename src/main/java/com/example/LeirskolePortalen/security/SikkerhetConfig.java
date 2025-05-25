package com.example.LeirskolePortalen.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class SikkerhetConfig {

        @Bean
        public SecurityFilterChain sikkerhetsFilter(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().permitAll()  // 👈 tillater alle requests uten innlogging
                    )
                    .formLogin().disable()           // 👈 deaktiverer login-form
                    .logout().disable()              // 👈 deaktiverer logout
                    .build();
        }


        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }



