package com.example.bloggingapp.security.jwt;

import com.example.bloggingapp.users.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationManager implements AuthenticationManager {
    private JWTService jwtService;
    private UserService userService;

    public JwtAuthenticationManager(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JwtAuthentication jwtAuthentication)
        {
            //TODO:TryCath CryptoFailure, jwtisExpired
            var jwtString=jwtAuthentication.getCredentials();
            var username=jwtService.getUserFromJwt(jwtString);
            var userResponseDto=userService.findUserByUsername(username);
            jwtAuthentication.setUser(userResponseDto);
            return jwtAuthentication;
        }
        return null;
    }
}
