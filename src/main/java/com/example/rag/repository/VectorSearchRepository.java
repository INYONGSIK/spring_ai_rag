
package com.example.rag.repository;

import com.example.rag.dto.DocumentChunk;
import com.example.rag.mapper.VectorSearchMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VectorSearchRepository {

 private final VectorSearchMapper mapper;

 public VectorSearchRepository(VectorSearchMapper mapper){
  this.mapper = mapper;
 }

 public List<DocumentChunk> search(List<Double> embedding, String question){

  String vector = embedding.toString();

  return mapper.search(vector, question);
 }

}
