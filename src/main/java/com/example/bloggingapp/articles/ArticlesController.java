package com.example.bloggingapp.articles;

import com.example.bloggingapp.articles.dtos.ArticleResponseDto;
import com.example.bloggingapp.articles.dtos.CreateArticleDto;
import com.example.bloggingapp.articles.dtos.UpdateArticleDto;
import com.example.bloggingapp.comments.dtos.CommentResponseDto;
import com.example.bloggingapp.comments.dtos.CreateCommentDto;
import com.example.bloggingapp.users.DTOs.UserResponseDto;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    private final ArticleService articleService;


    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public ResponseEntity<List<ArticleResponseDto>>getAllArticles(@AuthenticationPrincipal UserResponseDto userResponseDto,
                                                                  @RequestParam(required = false)String tag,
                                                                  @RequestParam(required = false)String author)
    {
        List<ArticleResponseDto>articleResponseDtos=articleService.getAllArticles();
        return ResponseEntity.ok(articleResponseDtos);
    }
    @GetMapping("/{slug}")
    public ResponseEntity<ArticleResponseDto>getArticlesBySlug(@AuthenticationPrincipal UserResponseDto userResponseDto,
                                                                     @PathVariable(name = "slug") String slug)
    {
        ArticleResponseDto articleResponseDtos=articleService.getArticlesBySlug(slug);
        return ResponseEntity.ok(articleResponseDtos);
    }
    @PostMapping("")
    public ResponseEntity<ArticleResponseDto>createNewArticle(@AuthenticationPrincipal UserResponseDto userResponseDto,
                                                              @RequestBody CreateArticleDto createArticleDto)
    {
        ArticleResponseDto articleResponseDto=articleService.createNewArticle(userResponseDto,createArticleDto);
        return ResponseEntity.created(URI.create("http://localhost:8383/articles/"+articleResponseDto.getId())).body(articleResponseDto);
    }
    @PatchMapping("/{slug}")
    public ResponseEntity<ArticleResponseDto>updateArticle(@AuthenticationPrincipal UserResponseDto user,
                                                           @RequestBody UpdateArticleDto updateArticle,
                                                           @PathVariable("slug") String slug)
    {
        ArticleResponseDto articleResponseDto=articleService.updateArticle(user, updateArticle,slug);
        return ResponseEntity.ok(articleResponseDto);
    }

    @GetMapping("/{slug}/comments")
    public ResponseEntity<List<CommentResponseDto>>getComments(@AuthenticationPrincipal UserResponseDto userResponseDto,
                                                               @PathVariable(name = "slug") String slug)
    {
        List<CommentResponseDto>commentResponseDtos=articleService.getComments(slug);
        return ResponseEntity.ok(commentResponseDtos);
    }
    @PostMapping("/{slug}/comments")
    public ResponseEntity<CommentResponseDto>postComment(@AuthenticationPrincipal UserResponseDto userResponseDto,
                                                         @PathVariable("slug") String slug,@RequestBody CreateCommentDto createCommentDto)
    {
        CommentResponseDto commentResponseDto=articleService.postComment(userResponseDto,slug,createCommentDto);
        return ResponseEntity.created(URI.create("http://localhost:8383/articles/"+slug+"/comments/"+commentResponseDto.getId())).body(commentResponseDto);
    }

    @DeleteMapping("/{slug}/comments/{comment-id}")
    public ResponseEntity<Void>deleteComment(@AuthenticationPrincipal UserResponseDto userResponseDto,
                                              @PathVariable("slug") String slug,@PathVariable("comment-id") Long commentId)
    {
        articleService.deleteComment(userResponseDto,slug,commentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{slug}/like")
    public ResponseEntity<ArticleResponseDto>favoriteArticle(@AuthenticationPrincipal UserResponseDto userResponseDto,
                                                              @PathVariable("slug") String slug)
    {
        ArticleResponseDto articleResponseDto=articleService.favoriteArticle(userResponseDto,slug);
        return ResponseEntity.ok(articleResponseDto);
    }

    @DeleteMapping("/{slug}/dislike")
    public ResponseEntity<Void>unfavoriteArticle(@AuthenticationPrincipal UserResponseDto userResponseDto,
                                                                @PathVariable("slug") String slug)
    {
        ArticleResponseDto articleResponseDto=articleService.unfavoriteArticle(userResponseDto,slug);
        return ResponseEntity.noContent().build();
    }

}
