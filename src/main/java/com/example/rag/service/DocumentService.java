package com.example.rag.service;

import com.example.rag.mapper.DocumentMapper;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final EmbeddingModel embeddingModel;
    private final DocumentMapper documentMapper;

    public DocumentService(EmbeddingModel embeddingModel,
                           DocumentMapper documentMapper) {
        this.embeddingModel = embeddingModel;
        this.documentMapper = documentMapper;
    }

    public void save(String content) {

        List<Double> embedding = embeddingModel.embed(content);

        String vector = embedding.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));

        documentMapper.insert(content, vector);
    }
}