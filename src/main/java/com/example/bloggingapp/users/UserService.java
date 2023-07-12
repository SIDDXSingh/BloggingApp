package com.example.bloggingapp.users;
import com.example.bloggingapp.common.ModelMapperList;
import com.example.bloggingapp.security.authToken.AuthTokenService;
import com.example.bloggingapp.security.jwt.JWTService;
import com.example.bloggingapp.users.DTOs.CreateUserDto;
import com.example.bloggingapp.users.DTOs.LoginUserDto;
import com.example.bloggingapp.users.DTOs.ProfileResponseDto;
import com.example.bloggingapp.users.DTOs.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final AuthTokenService authTokenService;
    private final JWTService jwtService;
    private final ModelMapperList modelMapperList;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthTokenService authTokenService, JWTService jwtService, ModelMapperList modelMapperList) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authTokenService = authTokenService;
        this.jwtService = jwtService;
        this.modelMapperList = modelMapperList;
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

    public List<ProfileResponseDto> getAllUsers() {
        List<UserEntity>userEntities=userRepository.findAll();
        List<ProfileResponseDto>profileResponseDtos=modelMapperList.mapList(userEntities,ProfileResponseDto.class);
        return profileResponseDtos;
    }

    public ProfileResponseDto getUser(String username) {
        UserEntity user=userRepository.findByUsername(username);
        ProfileResponseDto profileResponseDto=modelMapper.map(user,ProfileResponseDto.class);
        return profileResponseDto;
    }

    public void followUser(UserResponseDto userResponseDto, String username) {
        UserEntity user=userRepository.findByUsername(userResponseDto.getUsername());
        UserEntity followUser=userRepository.findByUsername(username);
        user.addFollower(followUser);
        followUser.addFollowee(user);
        userRepository.save(user);
        userRepository.save(followUser);

    }

    public void unFollowUser(UserResponseDto userResponseDto, String username) {
        UserEntity user=userRepository.findByUsername(userResponseDto.getUsername());
        UserEntity followUser=userRepository.findByUsername(username);
        user.removeFollower(followUser);
        followUser.removeFollowee(user);
        userRepository.save(user);
        userRepository.save(followUser);
    }
}
