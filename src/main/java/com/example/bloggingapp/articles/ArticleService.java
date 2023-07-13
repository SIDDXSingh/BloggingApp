package com.example.bloggingapp.articles;

import com.example.bloggingapp.articles.dtos.ArticleResponseDto;
import com.example.bloggingapp.articles.dtos.CreateArticleDto;
import com.example.bloggingapp.articles.dtos.UpdateArticleDto;
import com.example.bloggingapp.comments.CommentEntity;
import com.example.bloggingapp.comments.CommentRepository;
import com.example.bloggingapp.comments.dtos.CommentResponseDto;
import com.example.bloggingapp.comments.dtos.CreateCommentDto;
import com.example.bloggingapp.common.ModelMapperList;
import com.example.bloggingapp.users.DTOs.UserResponseDto;
import com.example.bloggingapp.users.UserEntity;
import com.example.bloggingapp.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperList modelMapperList;
    private final CommentRepository commentRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository,
                          ModelMapper modelMapper, ModelMapperList modelMapperList, CommentRepository commentRepository
        ) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.modelMapperList = modelMapperList;
        this.commentRepository = commentRepository;
    }
    private String getSlug(String title)
    {
        return title.toLowerCase(Locale.ROOT).replace(' ', '-');
    }

    public List<ArticleResponseDto> getAllArticles() {

        List<ArticleEntity>articleEntities=articleRepository.findAll();
        return modelMapperList.mapList(articleEntities,ArticleResponseDto.class);
    }

    public ArticleResponseDto getArticlesBySlug(String slug) {

        ArticleEntity article= articleRepository.findBySlug(slug).get(0);
        return modelMapper.map(article,ArticleResponseDto.class);
    }

    public ArticleResponseDto createNewArticle(UserResponseDto userResponseDto, CreateArticleDto createArticleDto) {

        ArticleEntity article=modelMapper.map(createArticleDto,ArticleEntity.class);
        article.setSlug(this.getSlug(article.getTitle()));
        UserEntity user=userRepository.findByUsername(userResponseDto.getUsername());
        article.setAuthor(user);
        return modelMapper.map(articleRepository.save(article),ArticleResponseDto.class);

    }

    public ArticleResponseDto updateArticle(UserResponseDto user, UpdateArticleDto updateArticleDto, String slug)
    {
        ArticleEntity article=articleRepository.findBySlug(slug).get(0);
        if(updateArticleDto.getTitle()!=null) {
            article.setTitle(updateArticleDto.getTitle());
            article.setSlug(this.getSlug(updateArticleDto.getTitle()));
        }
        if(updateArticleDto.getBody()!=null)
            article.setBody(updateArticleDto.getBody());
        if(updateArticleDto.getSubtitle()!=null)
            article.setSubtitle(updateArticleDto.getSubtitle());
        return modelMapper.map(articleRepository.save(article),ArticleResponseDto.class);
    }

    public List<CommentResponseDto> getComments(String slug) {
        ArticleEntity article=articleRepository.findBySlug(slug).get(0);
        List<CommentEntity>commentEntities=commentRepository.findByArticle(article);
        return modelMapperList.mapList(commentEntities,CommentResponseDto.class);
    }




    public CommentResponseDto postComment(UserResponseDto userResponseDto, String slug, CreateCommentDto createCommentDto) {
        ArticleEntity article=articleRepository.findBySlug(slug).get(0);
        UserEntity user=userRepository.findByUsername(userResponseDto.getUsername());
        CommentEntity comment=modelMapper.map(createCommentDto,CommentEntity.class);
        System.out.println(comment.getBody());
        System.out.println(createCommentDto.getBody());
        article.addComment(comment);
        comment.setArticle(article);
        comment.setCommenter(user);
        return modelMapper.map(commentRepository.save(comment),CommentResponseDto.class);
    }

    public void deleteComment(UserResponseDto userResponseDto, String slug, Long commentId) {
        ArticleEntity article=articleRepository.findBySlug(slug).get(0);
        UserEntity user=userRepository.findByUsername(userResponseDto.getUsername());
        CommentEntity comment=commentRepository.findById(commentId).orElseThrow();
        comment.setCommenter(null);
        comment.setArticle(null);
        commentRepository.delete(comment);
    }

    public ArticleResponseDto favoriteArticle(UserResponseDto userResponseDto, String slug)
    {
        ArticleEntity article=articleRepository.findBySlug(slug).get(0);
        UserEntity user=userRepository.findByUsername(userResponseDto.getUsername());
        article.addFan(user);
        return modelMapper.map(articleRepository.save(article),ArticleResponseDto.class);
    }

    public ArticleResponseDto unfavoriteArticle(UserResponseDto userResponseDto, String slug) {


        ArticleEntity article=articleRepository.findBySlug(slug).get(0);
        UserEntity user=userRepository.findByUsername(userResponseDto.getUsername());
        article.removeFan(user);
        return modelMapper.map(articleRepository.save(article),ArticleResponseDto.class);
    }
}
