package com.example.bloggingapp.articles.dtos;

import lombok.Data;

@Data
public class UpdateArticleDto {
    private String title;
    private String subtitle;
    private String body;
}
