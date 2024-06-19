package org.workat.workat_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.workat.workat_project.jwt.JWTFilter;
import org.workat.workat_project.jwt.JWTUtil;
import org.workat.workat_project.jwt.LoginFilter;
import org.workat.workat_project.user.repository.UserMapper;
import org.workat.workat_project.user.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration configuration;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserMapper userMapper;

    public SecurityConfig(AuthenticationConfiguration configuration, JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService, UserMapper userMapper) {
        this.configuration = configuration;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.userMapper = userMapper;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));

        LoginFilter loginFilter = new LoginFilter(authenticationManager, jwtUtil, userMapper);
        JWTFilter jwtFilter = new JWTFilter(jwtUtil, customUserDetailsService, userMapper);

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/place/**", "/signup/**", "/login/**", "/logout/**", "/user/**", "/review/**", "/payment/**", "/reserve/**", "/check-email/**").permitAll()
                        .requestMatchers("/partner/**").hasAuthority("ROLE_PARTNER")
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.disable())
                .addFilterBefore(jwtFilter, LoginFilter.class)
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
