package com.example.bloggingapp.comments.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class CommentResponseDto {
    Long id;
    String title;
    String body;
    Date createdAt;
}
