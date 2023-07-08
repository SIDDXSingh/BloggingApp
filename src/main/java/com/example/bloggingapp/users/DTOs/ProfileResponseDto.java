package com.example.bloggingapp.users.DTOs;

import lombok.Data;

@Data
public class ProfileResponseDto {
    private String username;
    private String bio;
    private String image;
    private String email;
}
