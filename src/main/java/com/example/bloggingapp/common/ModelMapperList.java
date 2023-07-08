package com.example.bloggingapp.common;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ModelMapperList {
    private final ModelMapper modelMapper;
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}