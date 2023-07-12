package com.example.bloggingapp.comments;

import com.example.bloggingapp.articles.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


    public List<CommentEntity>findByArticle(ArticleEntity articleEntity);
}
