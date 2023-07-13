package com.example.bloggingapp.users;


import com.example.bloggingapp.users.DTOs.CreateUserDto;
import com.example.bloggingapp.users.DTOs.LoginUserDto;
import com.example.bloggingapp.users.DTOs.ProfileResponseDto;
import com.example.bloggingapp.users.DTOs.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto>createUser(@RequestBody CreateUserDto createUserDto)
    {
        UserResponseDto createdUser=userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/users/"+createdUser.getId())).body(createdUser);
    }
    @PostMapping("/users/login")
    public ResponseEntity<UserResponseDto>verifyUser(@RequestBody LoginUserDto loginUserDto)
    {
        UserResponseDto userResponseDto=userService.verifyUser(loginUserDto);
        return ResponseEntity.created(URI.create("/users/"+userResponseDto.getId())).body(userResponseDto);
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileResponseDto>>getAllUsers()
    {
        List<ProfileResponseDto> profileResponseDtos=userService.getAllUsers();
        return ResponseEntity.ok(profileResponseDtos);
    }

    @GetMapping("/profiles/{username}")
    public ResponseEntity<ProfileResponseDto>getUserProfile(@PathVariable(name = "username") String username)
    {
        ProfileResponseDto profileResponseDto=userService.getUser(username);
        return ResponseEntity.ok(profileResponseDto);
    }
    @PostMapping("/profiles/{username}/follow")
    public ResponseEntity<Void>followUser(@PathVariable(name = "username") String username,
                                                        @AuthenticationPrincipal UserResponseDto userResponseDto)
    {
        userService.followUser(userResponseDto,username);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/profiles/{username}/follow")
    public ResponseEntity<Void>unfollowUser(@PathVariable(name = "username") String username,
                                          @AuthenticationPrincipal UserResponseDto userResponseDto)
    {
        userService.unFollowUser(userResponseDto,username);
        return ResponseEntity.ok().build();
    }

}
