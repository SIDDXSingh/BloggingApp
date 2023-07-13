package com.example.bloggingapp.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
    @CreationTimestamp
    protected Date createdAt;

    @Column(nullable=true)
    @UpdateTimestamp
    protected Date updateAt;
}
