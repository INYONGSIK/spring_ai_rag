
package com.example.rag.mapper;

import com.example.rag.dto.DocumentChunk;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VectorSearchMapper {

 List<DocumentChunk> search(@Param("vector") String vector , @Param("question") String question);

}
