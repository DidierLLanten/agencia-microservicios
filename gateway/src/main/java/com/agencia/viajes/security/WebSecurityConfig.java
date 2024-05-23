package com.agencia.viajes.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(e ->
                e.pathMatchers("/api/test/anonymous").permitAll()
                        .pathMatchers("/api/test/admin").hasRole(ADMIN)
                        .pathMatchers("/api/test/user").hasAnyRole(ADMIN, USER)
                        .pathMatchers("/api/persona/**").hasRole(ADMIN)
                        .pathMatchers("/api/destino/**").hasRole(ADMIN)
                        .pathMatchers("/api/alojamiento/**").hasRole(ADMIN)
                        .pathMatchers("/api/vuelo/**").hasRole(ADMIN)
                        .pathMatchers("/api/reserva/**").hasRole(USER)
                        .pathMatchers("/api/auth/**").permitAll()
                        .anyExchange().authenticated());

        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.csrf().disable();
        return http.build();
    }
}

