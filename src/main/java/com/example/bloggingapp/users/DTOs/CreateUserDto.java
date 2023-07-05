package com.example.bloggingapp.users.DTOs;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String email;
    private String password;
}
