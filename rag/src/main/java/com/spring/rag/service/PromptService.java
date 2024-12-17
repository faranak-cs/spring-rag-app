package com.spring.rag.service;

import com.spring.rag.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PromptService {

    @Value("classpath:prompts/shoppingAssistantPrompt.st")
    private Resource shoppingAssistantPrompt;

    private final EmbeddingModel embeddingModel;
    private final PGVectorService pgVectorService;

    // Generate prompt using user query
    public Prompt generatePromptUsingUserQuery(String userQuery){

        // Call embedding function
        float[] userQueryEmbedding = embeddingModel.embed(userQuery);

        // Call PGVectorService: Query products table
        List<Product> similarProducts = pgVectorService.getProductsUsingUserQuery(userQueryEmbedding);
        String similarProductsString = getProductsString(similarProducts);
        log.info("Similar products: {}", similarProductsString);

        // Generate prompt
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(shoppingAssistantPrompt);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("products", similarProductsString));
        log.info("System message: {}", systemMessage.getContent());

        UserMessage userMessage = new UserMessage(userQuery);
        log.info("User message: {}", userMessage);

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        log.info("Prompt: {}", prompt);

        // Return the response
        return prompt;
    }

    // Get products string
    public String getProductsString(List<Product> similarProducts){
        String products = similarProducts
                .stream()
                .map(Product::getProductname)
                .collect(Collectors.joining("\n"));
        return products;
    }
}