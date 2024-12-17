package com.spring.rag.service;

import com.spring.rag.service.proto.ChatGrpc;
import com.spring.rag.service.proto.LLMResponse;
import com.spring.rag.service.proto.UserQuery;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;

@GrpcService
@RequiredArgsConstructor
public class ChatImpl extends ChatGrpc.ChatImplBase {

    private final PromptService promptService;
    private final ChatGeneratorService chatGeneratorService;

    @Override
    public StreamObserver<UserQuery> chatWithLLM(StreamObserver<LLMResponse> responseObserver) {
        return new StreamObserver<UserQuery>() {
            @Override
            public void onNext(UserQuery userQuery) {
                Prompt prompt = promptService.generatePromptUsingUserQuery(userQuery.getQuery());
                chatGeneratorService.generateStream(prompt)
                        .subscribe(s -> responseObserver.onNext(getLLMResponseFromChatResponse(s)),
                                responseObserver::onError,
                                () -> { }
                        );
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    private LLMResponse getLLMResponseFromChatResponse (ChatResponse chatResponse){
        return LLMResponse.newBuilder().setResponse(chatResponse.getResult().getOutput().getContent()).build();
    }

}