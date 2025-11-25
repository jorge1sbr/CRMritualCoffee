package com.ritualcoffee.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // === 1. Registramos BCrypt para encriptar contraseñas ===
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // === 2. Configuración de seguridad básica ===
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desactivamos CSRF para permitir peticiones desde Angular/Postman
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // de momento, TODO permitido
            )
            .formLogin(form -> form.disable()) // quitamos login de formulario por defecto
            .httpBasic(httpBasic -> httpBasic.disable()); // quitamos basic auth

        return http.build();
    }
}
