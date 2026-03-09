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
    private final ChunkService chunkService;

    public DocumentService(EmbeddingModel embeddingModel,
                           DocumentMapper documentMapper,
                           ChunkService chunkService) {

        this.embeddingModel = embeddingModel;
        this.documentMapper = documentMapper;
        this.chunkService = chunkService;
    }

    public void save(String content) {

        // 1️⃣ chunk 분리
        List<String> chunks = chunkService.split(content);

        // 2️⃣ chunk마다 embedding 생성
        for (String chunk : chunks) {

            List<Double> embedding = embeddingModel.embed(chunk);

            String vector = embedding.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",", "[", "]"));

            // 3️⃣ DB 저장
            documentMapper.insert(chunk, vector);
        }
    }
}