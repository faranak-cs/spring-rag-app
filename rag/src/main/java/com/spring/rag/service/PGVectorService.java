package com.spring.rag.service;

import com.spring.rag.model.Product;
import com.spring.rag.repository.EmbeddingRepository;
import com.spring.rag.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PGVectorService {
    // Database calls here

    private final ProductRepository productRepository;
    private final EmbeddingRepository embeddingRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void storeEmbedding(Integer id, float[] embedding){
        System.out.println(id + " " + embedding.length);

        // create a function in embeddingRepository to store embedding in embeddings table
        embeddingRepository.insertEmbedding(id, embedding);
    }

    public List<Product> getProductsUsingUserQuery(float[] embedding){
        // create a function in productRepository to compare embedding in products table
        return productRepository.getSimilarProducts(embedding);
    }

    public List<Product> getProductsUsingProductName(String productName) {
        // create a function in productRepository to compare product in products table
        return productRepository.getExactProducts(productName);
    }
}