//package com.example.rag.config;
//
//import org.springframework.ai.embedding.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ai.document.Document;
//
//import java.util.List;
//
//@Configuration
//public class EmbeddingConfig {
//
//    @Bean
//    public EmbeddingModel embeddingModel() {
//
//        return new EmbeddingModel() {
//
//            @Override
//            public EmbeddingResponse call(EmbeddingRequest request) {
//                throw new UnsupportedOperationException(
//                        "Embedding handled by HuggingFace API"
//                );
//            }
//
//            @Override
//            public List<Double> embed(Document document) {
//                throw new UnsupportedOperationException(
//                        "Embedding handled by HuggingFace API"
//                );
//            }
//        };
//    }
//}