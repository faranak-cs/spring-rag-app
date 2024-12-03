package com.spring.rag.service;

import com.spring.rag.model.Product;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OllamaEmbeddingService {

    private static final Logger logger = LoggerFactory.getLogger(OllamaEmbeddingService.class);
    private final EmbeddingModel embeddingModel;
    private final PGVectorService pgVectorService;

    // Generate String Embeddings
    public float[] generateStringEmbedding(String message){
        float[] messageEmbedding = embeddingModel.embed(message);
        return messageEmbedding;
    }

    // Generate Description Embeddings
    public void generateDescriptionEmbedding(){
        // Call PGVectorService: Query products table
        List<Product> products = pgVectorService.getAllProducts();
        logger.info("Called PGVectorService.getAllProducts()");

        // Call embedding function
        for (Product product : products){
            float[] productDescriptionEmbedding = embeddingModel.embed(product.getProductdescription());

            // Call PGVectorService: Store into embeddings table
            pgVectorService.storeEmbedding(product.getProductid(), productDescriptionEmbedding);
            logger.info("Called PGVectorService.storeEmbedding()");
        }
    }

    // Get similar products based on product name
    public List<Product> getProductsUsingProductName(String productName) {
        // Call PGVectorService: Query products table
        List<Product> exactProducts = pgVectorService.getProductsUsingProductName(productName);

        // Return the retrieved products based on similarity
        return exactProducts;
    }

    // Get similar products based on user query
    public List<Product> getProductsUsingUserQuery(String userQuery) {
        // Call embedding function
        float[] userQueryEmbedding = embeddingModel.embed(userQuery);

        // Call PGVectorService: Query products table
        List<Product> similarProducts = pgVectorService.getProductsUsingUserQuery(userQueryEmbedding);

        // Return the retrieved products based on similarity
        return similarProducts;
    }
}
