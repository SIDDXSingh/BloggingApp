package com.example.bloggingapp.comments.dtos;

import lombok.Data;

@Data
public class CreateCommentDto {
    public String title;
    public String body;
}
