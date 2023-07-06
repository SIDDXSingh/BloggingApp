package com.example.bloggingapp.users;


import com.example.bloggingapp.articles.ArticleEntity;
import comments.CommentEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String bio;


    @ManyToMany
    @JoinColumn(name = "follower_id")
    private List<UserEntity> follower;

    @ManyToMany
    @JoinColumn(name = "followee_id")
    private List<UserEntity>followee;


    @OneToMany
    List<ArticleEntity>articles;

    @ManyToMany
    List<ArticleEntity>fans;

    @OneToMany
    List<CommentEntity>comments;
}
