package com.example.rag.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkService {

    private static final int MAX_LENGTH = 500;
    private static final int OVERLAP = 100;

    public List<String> split(String text) {

        String[] sentences = text.split("(?<=[.!?])|\\n");

        List<String> chunks = new ArrayList<>();

        StringBuilder current = new StringBuilder();

        for (String sentence : sentences) {

            sentence = sentence.trim();

            if (sentence.isEmpty()) {
                continue;
            }

            if (current.length() + sentence.length() > MAX_LENGTH) {

                String chunk = current.toString().trim();

                if (!chunk.isEmpty()) {
                    chunks.add(chunk);
                }

                String overlap = current.substring(
                        Math.max(0, current.length() - OVERLAP)
                );

                current = new StringBuilder(overlap);
            }

            current.append(sentence).append(" ");
        }

        if (current.length() > 0) {

            String chunk = current.toString().trim();

            if (!chunk.isEmpty()) {
                chunks.add(chunk);
            }
        }

        return chunks;
    }
}