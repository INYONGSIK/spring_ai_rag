
package com.example.rag.controller;

import com.example.rag.dto.DocumentChunk;
import com.example.rag.repository.VectorSearchRepository;
import com.example.rag.service.ChatService;
import com.example.rag.service.EmbeddingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rag")
public class RagController {

 private final EmbeddingService embeddingService;
 private final VectorSearchRepository repository;
 private final ChatService chatService;

 public RagController(
         EmbeddingService embeddingService,
         VectorSearchRepository repository,
         ChatService chatService) {

  this.embeddingService = embeddingService;
  this.repository = repository;
  this.chatService = chatService;
 }

 @PostMapping("/ask")
 public String ask(@RequestBody String question){

  List<Double> vector = embeddingService.embed(question);
  List<DocumentChunk> docs = repository.search(vector, question);

  return chatService.ask(question,
          docs.stream().map(DocumentChunk::getContent).toList());
 }

}
