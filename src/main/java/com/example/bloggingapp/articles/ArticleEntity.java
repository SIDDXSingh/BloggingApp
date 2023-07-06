package com.example.bloggingapp.articles;

import com.example.bloggingapp.Tags.TagEntity;
import com.example.bloggingapp.users.UserEntity;
import comments.CommentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private Date createdAt;

    @Column
    @ManyToMany
    private List<TagEntity>tags;


    @ManyToOne
    private UserEntity Author;

    @ManyToMany
    private List<UserEntity>likedByUsers;

    @OneToMany
    private List<CommentEntity>comments;

}
