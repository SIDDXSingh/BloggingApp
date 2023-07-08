package com.example.bloggingapp.Tags;


import com.example.bloggingapp.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagEntity extends BaseEntity {
    @Column(nullable = false)
    String name;
}
