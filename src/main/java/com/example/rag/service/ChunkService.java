package com.example.rag.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkService {

    private static final int MAX_LENGTH = 500;
    private static final int OVERLAP = 100;

    public List<String> split(String text) {

        String[] sentences = text.split("(?<=[.!?])");

        List<String> chunks = new ArrayList<>();

        StringBuilder current = new StringBuilder();

        for (String sentence : sentences) {

            if (current.length() + sentence.length() > MAX_LENGTH) {

                chunks.add(current.toString());

                String overlap = current.substring(
                        Math.max(0, current.length() - OVERLAP)
                );

                current = new StringBuilder(overlap);
            }

            current.append(sentence);
        }

        if (!current.isEmpty()) {
            chunks.add(current.toString());
        }

        return chunks;
    }
}