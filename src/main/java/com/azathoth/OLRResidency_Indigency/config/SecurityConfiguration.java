package com.azathoth.OLRResidency_Indigency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/residents/public/**").permitAll() // permit all public endpoints (for normal users)
                        .requestMatchers("api/admin/private/**").hasAuthority("ROLE_ADMIN") // only user with admin role can access private endpoints
                        .anyRequest().authenticated()
                )
                .oauth2Login(login -> login
                        .defaultSuccessUrl("/api/admin/private/dashboard") // redirect to admin dashboard if successful login
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // redirect after logout
                );

        return httpSecurity.build();
    }


}
