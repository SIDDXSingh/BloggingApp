package com.example.bloggingapp.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    protected Long id;

    @Column(nullable=true)
    protected Date createdAt;
    @Column(nullable=true)
    protected Date updateAt;
}
