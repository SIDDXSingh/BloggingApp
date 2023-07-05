package com.example.bloggingapp.users.DTOs;


import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String token;
}
