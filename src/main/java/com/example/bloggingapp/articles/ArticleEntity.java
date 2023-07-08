package com.example.bloggingapp.articles;

import com.example.bloggingapp.Tags.TagEntity;
import com.example.bloggingapp.common.BaseEntity;
import com.example.bloggingapp.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.bloggingapp.comments.CommentEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name="article")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(nullable = false)
    private String body;

    @Column
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TagEntity>tags;

    @ManyToOne(targetEntity = UserEntity.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @JsonBackReference
    private UserEntity author;

    @ManyToMany(targetEntity = UserEntity.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "favorites",joinColumns = @JoinColumn(name = "article_id"),inverseJoinColumns = @JoinColumn(name = "users_id"))
    @JsonBackReference
    private List<UserEntity>fans;

    @OneToMany(targetEntity = CommentEntity.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "article")
    @JsonManagedReference
    private List<CommentEntity>comments;
}
