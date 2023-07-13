package com.example.bloggingapp.articles.dtos;

import com.example.bloggingapp.Tags.TagEntity;
import lombok.Data;

import java.util.List;

@Data
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String subtitle;
    private String body;
    private String slug;
    private List<TagEntity> tags;
}
