package com.example.bloggingapp.common;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@MappedSuperclass
public abstract class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    protected Long id;

    protected Date createdAt;
    protected Date updateAt;
}
