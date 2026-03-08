package com.example.rag.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DocumentMapper {

    void insert(@Param("content") String content,
                @Param("embedding") String embedding);
}