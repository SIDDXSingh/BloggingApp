package com.example.bloggingapp.users;


import com.example.bloggingapp.articles.ArticleEntity;
import com.example.bloggingapp.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.bloggingapp.comments.CommentEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String bio;
    @Column
    private String image;


    @ManyToMany
    @JoinColumn(name = "follower_id")
    private List<UserEntity> follower;

    @ManyToMany
    @JoinColumn(name = "followee_id")
    private List<UserEntity>followee;


    @OneToMany(mappedBy = "author",targetEntity = ArticleEntity.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    List<ArticleEntity>articles;

    @ManyToMany(mappedBy = "fans",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<ArticleEntity>favorites;

    @OneToMany(mappedBy = "commenter",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    List<CommentEntity>comments;

    public void addFollower(UserEntity user)
    {
        this.follower.add(user);
    }
    public void addArticle(ArticleEntity article)
    {
        this.articles.add(article);
    }

    public void addFollowee(UserEntity user)
    {
        this.followee.add(user);
    }

    public void removeFollower(UserEntity user)
    {

        this.follower.remove(user);
    }
    public void removeFollowee(UserEntity user)
    {
        this.followee.remove(user);
    }


    public void addComment(CommentEntity comment) {
        this.comments.add(comment);
    }

    public void removeComment(CommentEntity comment) {
        this.comments.remove(comment);
    }
}
