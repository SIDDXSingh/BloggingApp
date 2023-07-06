package com.example.bloggingapp.security.jwt;


import com.example.bloggingapp.users.DTOs.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private final String jwtString;
    private UserResponseDto user;

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public JwtAuthentication(String jwtString) {
        this.jwtString = jwtString;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO:Not needed unless different resources needed
        return null;
    }

    @Override
    public String getCredentials() {
        //This is where we return the string/token for authentication
        return jwtString;
    }

    @Override
    public Object getDetails() {
        //TODO:Not needed
        return null;
    }

    @Override
    public UserResponseDto getPrincipal() {
        //This is where we return user/client getting authenticated
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return (user!=null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
