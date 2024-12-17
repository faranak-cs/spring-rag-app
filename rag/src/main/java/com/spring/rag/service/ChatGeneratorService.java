package com.spring.rag.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@Service
@Getter
public class ChatGeneratorService {

    private final StreamingChatModel streamingChatModel;
    private final ChatModel chatModel;

    public ChatResponse generate(Prompt prompt) {
        return getChatModel().call(prompt);
    }

    public Flux<ChatResponse> generateStream(Prompt prompt) {
        return getStreamingChatModel().stream(prompt);
    }
}