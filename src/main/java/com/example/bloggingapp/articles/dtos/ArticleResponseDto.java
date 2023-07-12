package com.example.bloggingapp.articles.dtos;

import lombok.Data;

@Data
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String subtitle;
    private String body;
    private String slug;
}
