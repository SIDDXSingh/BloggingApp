package com.example.bloggingapp.comments;

import com.example.bloggingapp.articles.ArticleEntity;
import com.example.bloggingapp.common.BaseEntity;
import com.example.bloggingapp.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String body;

    @ManyToOne(targetEntity = UserEntity.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserEntity commenter;


    @ManyToOne(targetEntity = ArticleEntity.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name ="article_id")
    private ArticleEntity article;
}
