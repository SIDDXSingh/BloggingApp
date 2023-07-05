package com.example.bloggingapp.users;
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

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder)
    {
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
        this.passwordEncoder=passwordEncoder;
    }

    public UserResponseDto createUser(CreateUserDto createUserDto)
    {
        UserEntity user=modelMapper.map(createUserDto,UserEntity.class);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        return modelMapper.map(userRepository.save(user),UserResponseDto.class);
    }

    public UserResponseDto verifyUser(LoginUserDto loginUserDto)
    {
        UserEntity user=userRepository.findByUsername(loginUserDto.getUsername());
        if(user==null)
            throw new RuntimeException();
        if(!passwordEncoder.matches(loginUserDto.getPassword(),user.getPassword()))
            throw new RuntimeException();
        else
            return modelMapper.map(user, UserResponseDto.class);
    }


}
