package com.example.bloggingapp.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    public static final String SECRET="aafafafatw554d68a4fa56d4a68ffa";
    Algorithm algorithm=Algorithm.HMAC256(SECRET);
    public String createJwt(String username)
    {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }
    public String getUserFromJwt(String token)
    {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

    }
}
