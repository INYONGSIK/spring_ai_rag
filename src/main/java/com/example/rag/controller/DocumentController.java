package com.example.rag.controller;

import com.example.rag.service.DocumentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // 기존 텍스트 저장
    @PostMapping
    public String save(@RequestBody Map<String, String> body) {

        String content = body.get("content");

        documentService.save(content);

        return "ok";
    }

    // 추가 : 파일 업로드
    @PostMapping("/file")
    public String uploadFile(@RequestParam MultipartFile file) throws Exception {

        documentService.saveFile(file);

        return "file stored";
    }
}