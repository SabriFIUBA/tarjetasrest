package com.scortinas.tarjetasrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Deshabilita CSRF usando la nueva sintaxis.
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/api/tarjetas/**").permitAll() // Permitir acceso público a tarjetas.
            .requestMatchers("/api/operaciones/**").permitAll() 
            .anyRequest().authenticated() // Proteger cualquier otro endpoint.
            )
            .httpBasic(Customizer.withDefaults()); // Usar autenticación básica con la configuración predeterminada.
        return http.build();
    }
}
