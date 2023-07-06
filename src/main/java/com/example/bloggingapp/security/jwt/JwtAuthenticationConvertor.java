package com.example.bloggingapp.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class JwtAuthenticationConvertor implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        var authHeader=request.getHeader("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer "))
        {
            var token=authHeader.substring(7);
            return new JwtAuthentication(token);
        }
        return null;
    }
}
