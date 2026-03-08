package com.example.rag.controller;

import com.example.rag.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public String save(@RequestBody Map<String, String> body) {

        String content = body.get("content");

        documentService.save(content);

        return "ok";
    }
}