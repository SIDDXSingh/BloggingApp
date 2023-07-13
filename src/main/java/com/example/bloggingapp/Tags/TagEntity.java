package com.example.bloggingapp.Tags;


import com.example.bloggingapp.articles.ArticleEntity;
import com.example.bloggingapp.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagEntity extends BaseEntity {
    @Column
    private String name;

}
