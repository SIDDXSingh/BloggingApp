package com.example.bloggingapp.users;
import com.example.bloggingapp.security.authToken.AuthTokenService;
import com.example.bloggingapp.security.jwt.JWTService;
import com.example.bloggingapp.users.DTOs.CreateUserDto;
import com.example.bloggingapp.users.DTOs.LoginUserDto;
import com.example.bloggingapp.users.DTOs.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final AuthTokenService authTokenService;
    private final JWTService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthTokenService authTokenService, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authTokenService = authTokenService;
        this.jwtService = jwtService;
    }


    public UserResponseDto createUser(CreateUserDto createUserDto)
    {
        UserEntity user=modelMapper.map(createUserDto,UserEntity.class);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        var savedUser=userRepository.save(user);
        var response=modelMapper.map(savedUser,UserResponseDto.class);
        // Option 1: Server_Side_Token_Method:
        // var token=authTokenService.createToken(savedUser);
        //Option 2: JWT
        var token=jwtService.createJwt(savedUser.getUsername());
        response.setToken(token);
        return response;
    }

    public UserResponseDto verifyUser(LoginUserDto loginUserDto)
    {
        UserEntity user=userRepository.findByUsername(loginUserDto.getUsername());
        if(user==null)
            throw new RuntimeException();
        if(!passwordEncoder.matches(loginUserDto.getPassword(),user.getPassword()))
            throw new RuntimeException();
        var response=modelMapper.map(user, UserResponseDto.class);
        response.setToken(jwtService.createJwt(user.getUsername()));
        return response;
    }


    public UserResponseDto findUserByUsername(String username) {
        var user=userRepository.findByUsername(username);
        return modelMapper.map(user, UserResponseDto.class);
    }
}
