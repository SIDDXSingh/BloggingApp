package com.example.bloggingapp.articles.dtos;

import com.example.bloggingapp.Tags.TagEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class CreateArticleDto {

    private String title;
    private String subtitle;
    private String body;
    private List<TagEntity>tags;
}
