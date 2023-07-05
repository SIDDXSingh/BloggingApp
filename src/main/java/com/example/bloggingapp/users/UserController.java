package com.example.bloggingapp.users;


import com.example.bloggingapp.users.DTOs.CreateUserDto;
import com.example.bloggingapp.users.DTOs.LoginUserDto;
import com.example.bloggingapp.users.DTOs.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService=userService;
    }



    @PostMapping("")
    public ResponseEntity<UserResponseDto>createUser(@RequestBody CreateUserDto createUserDto)
    {
        UserResponseDto createdUser=userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/users/"+createdUser.getId())).body(createdUser);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto>verifyUser(@RequestBody LoginUserDto loginUserDto)
    {
        UserResponseDto userResponseDto=userService.verifyUser(loginUserDto);
        return ResponseEntity.created(URI.create("/users/"+userResponseDto.getId())).body(userResponseDto);
    }

}
