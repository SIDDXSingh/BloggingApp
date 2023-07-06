package com.example.bloggingapp.security;


import com.example.bloggingapp.security.jwt.JWTAuthenticationFilter;
import com.example.bloggingapp.security.jwt.JWTService;
import com.example.bloggingapp.security.jwt.JwtAuthenticationConvertor;
import com.example.bloggingapp.security.jwt.JwtAuthenticationManager;
import com.example.bloggingapp.users.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig{
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    public AppSecurityConfig(JWTService jwtService, UserService usersService) {

        this.jwtAuthenticationFilter = new JWTAuthenticationFilter(
                new JwtAuthenticationManager(
                        jwtService, usersService
                )
        )
        ;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.
                cors(AbstractHttpConfigurer::disable).
                csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(HttpMethod.GET,"/about").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users","/users/login").permitAll()
                                .requestMatchers("/**").authenticated())
                .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .sessionManagement((sessionManagement) ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

}
