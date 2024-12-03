package com.spring.rag.controller;

import com.spring.rag.service.OllamaEmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/spring/ai/embedding")
public class EmbeddingController {

    private final OllamaEmbeddingService ollamaEmbeddingService;

    @GetMapping("/string")
    public ResponseEntity<float[]> embedString(@RequestParam() String message){
        float[] messageEmbedding = ollamaEmbeddingService.generateStringEmbedding(message);
        return ResponseEntity.status(200).body(messageEmbedding);
    }

    @GetMapping("/products")
    public ResponseEntity<String> embedProducts(){
        ollamaEmbeddingService.generateDescriptionEmbedding();
        return ResponseEntity.status(200).body("SUCCESS!");
    }
}
