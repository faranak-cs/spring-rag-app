package com.spring.rag.controller;

import com.spring.rag.service.ChatGeneratorService;
import com.spring.rag.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/spring/ai/llm")
public class LLMController {

    private final PromptService promptService;
    private final ChatGeneratorService chatGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<String> getResponseUsingUserQuery(@RequestBody String userQuery){
        Prompt prompt = promptService.generatePromptUsingUserQuery(userQuery);
        ChatResponse response = chatGeneratorService.generate(prompt);
        return ResponseEntity.status(200).body(getContentFromChatResponse(response));
    }

    public String getContentFromChatResponse(ChatResponse chatResponse){
        return chatResponse.getResult().getOutput().getContent();
    }

}