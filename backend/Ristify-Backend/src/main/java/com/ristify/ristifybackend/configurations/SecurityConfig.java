package com.ristify.ristifybackend.configurations;

import com.ristify.ristifybackend.controller.ConcreteMessageController;
import com.ristify.ristifybackend.models.user.UserRole;
import com.ristify.ristifybackend.utils.AppUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeCustomizer -> authorizeCustomizer
                        .requestMatchers(AppUtils.WHITE_LIST_URLS).permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/songs/**").hasRole(UserRole.Artist.name())
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(exceptionCustomizer -> exceptionCustomizer
                        .accessDeniedHandler(((request, response, accessDeniedException) ->
                                ConcreteMessageController.setServletResponse(response, AppUtils.ACCESS_DENIED, HttpServletResponse.SC_FORBIDDEN)))
                )
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}