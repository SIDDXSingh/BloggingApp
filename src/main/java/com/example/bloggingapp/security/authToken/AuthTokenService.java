package com.example.bloggingapp.security.authToken;

import com.example.bloggingapp.users.UserEntity;
import com.example.bloggingapp.users.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    private final AuthTokenRepository authTokenRepository;
    private final UserRepository userRepository;

    public AuthTokenService(AuthTokenRepository authTokenRepository, UserRepository userRepository) {
        this.authTokenRepository = authTokenRepository;
        this.userRepository = userRepository;
    }

    public String createToken(UserEntity user)
    {
        AuthTokenEntity authTokenEntity=new AuthTokenEntity();
        authTokenEntity.setUser(user);
        authTokenRepository.save(authTokenEntity);
        return authTokenEntity.getToken().toString();
    }

    public UserEntity getUserFromToken(String token)
    {
        AuthTokenEntity authTokenEntity=authTokenRepository.findById(token).orElseThrow();
        return authTokenEntity.getUser();
    }

}
