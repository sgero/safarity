package com.example.safarity.security.config;

import com.example.safarity.model.enums.Rol;
import com.example.safarity.security.filter.JWTFilterChain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTFilterChain jwtFilterChain;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/usuario/crear").permitAll()
                                .requestMatchers("/organizacion/listar").permitAll()
                                .requestMatchers("/evento/**").permitAll()
                                .requestMatchers("/organizacion/crear").permitAll()
                                .requestMatchers("/participante/listar").permitAll()
                                .requestMatchers("/participante/crear").permitAll()
                                .requestMatchers("/organizacion/eliminar").permitAll()
                                .requestMatchers("/organizacion/buscar").permitAll()
                                .requestMatchers("/organizacion/mostrarcalculado").permitAll()
                                .requestMatchers("/ticket/**").permitAll()
                                .requestMatchers("/asistente/**").permitAll()
                                .requestMatchers("/usuario/listar").hasAnyAuthority(Rol.ADMIN.name())
                                .requestMatchers("/admin/**").hasAnyAuthority(Rol.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();

    }


}