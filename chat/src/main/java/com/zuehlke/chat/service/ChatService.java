package com.zuehlke.chat.service;

import com.zuehlke.chat.model.ChatMessage;
import com.zuehlke.chat.model.MessageType;
import com.zuehlke.chat.repository.ChatMessageRepository;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.FunctionMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class ChatService {

    private final String model;

    private final OpenAiChatModel chatModel;
    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository,
                       @Value("${spring.ai.openai.base-url}") String endpoint,
                       @Value("${spring.ai.openai.api-key}") String apiKey,
                       @Value("${spring.ai.openai.chat.options.model}") String model) {
        this.chatMessageRepository = chatMessageRepository;
        this.model = model;

        OpenAiApi openAiAPi = new OpenAiApi(endpoint, apiKey);
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .withModel(this.model)
                .build();

        this.chatModel = new OpenAiChatModel(openAiAPi, openAiChatOptions);
    }

    public String generateResponse(String sessionId, String message) {
        List<ChatMessage> persistedMessages = this.chatMessageRepository.findBySessionId(sessionId);
        List<Message> messages = new ArrayList<>();

        persistedMessages.sort(Comparator.comparing(ChatMessage::getTimestamp));

        for (ChatMessage chatMessage : persistedMessages) {
            System.out.println("CONTENT: " + chatMessage.getContent());
            switch (chatMessage.getType()) {
                case USER:
                    messages.add(new UserMessage(chatMessage.getContent()));
                    break;
                case ASSISTANT:
                    messages.add(new AssistantMessage(chatMessage.getContent()));
                    break;
                case FUNCTION:
                    messages.add(new FunctionMessage(chatMessage.getContent()));
                    break;
                case SYSTEM:
                    messages.add(new SystemMessage(chatMessage.getContent()));
                    break;
            }
        }

        Message newMessage = new UserMessage(message);

        ChatMessage newChatMessage = new ChatMessage(newMessage.getContent(), sessionId, MessageType.USER, LocalDateTime.now());
        this.chatMessageRepository.save(newChatMessage);

        messages.add(new UserMessage(message));
        System.out.println("MESSAGES Count: " + messages.size());
        ChatResponse response = chatModel.call(new Prompt(messages));
        Message responseMessage = response.getResults().get(0).getOutput();

        ChatMessage responseChatMessage = new ChatMessage(responseMessage.getContent(), sessionId, MessageType.ASSISTANT, LocalDateTime.now());
        this.chatMessageRepository.save(responseChatMessage);

        return responseMessage.getContent();
    }
}
