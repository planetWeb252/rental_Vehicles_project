package com.rental.vehicles.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Público
//                        .requestMatchers(HttpMethod.GET, "/api/public/vehicle/allVehicles").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/api/public/employee/register/admin").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/api/public/employee/register/employeeOthers").permitAll()
//                        .requestMatchers(HttpMethod.POST,"api/public/customer").permitAll()
//                        .requestMatchers(HttpMethod.POST,"api/public/customer/allCustomer").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/api/public/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/public/vehicle").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/api/public/vehicle/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/public/vehicle/allVehicles").permitAll()
//
//                        // Clientes autenticados
//                        .requestMatchers("/api/private/**").hasAuthority("LOGIN")
                        .anyRequest().permitAll()

                );
                //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
