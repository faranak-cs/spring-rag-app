package com.spring.rag.controller;

import com.spring.rag.model.Product;
import com.spring.rag.service.OllamaEmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/spring/ai/user")
public class UserQueryController {

    private final OllamaEmbeddingService ollamaEmbeddingService;

    @GetMapping("/query")
    public ResponseEntity<List<Product>> getProductsUsingUserQuery(@RequestParam() String query){
        List<Product> similarProducts = ollamaEmbeddingService.getProductsUsingUserQuery(query);
        return ResponseEntity.status(200).body(similarProducts);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductsUsingProductName(@RequestParam() String productName){
        List<Product> exactProducts = ollamaEmbeddingService.getProductsUsingProductName(productName);
        return ResponseEntity.status(200).body(exactProducts);
    }
}