package com.example.bloggingapp;

import com.example.bloggingapp.common.ModelMapperList;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BloggingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggingAppApplication.class, args);
    }

    @Bean
    @Scope("singleton")
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ModelMapperList modelMapperList()
    {
        return new ModelMapperList(new ModelMapper());
    }
}
