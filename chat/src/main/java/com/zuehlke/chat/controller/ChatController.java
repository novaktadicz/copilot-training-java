package com.zuehlke.chat.controller;

import com.zuehlke.chat.dto.ChatRequest;
import com.zuehlke.chat.service.ChatService;
import com.zuehlke.chat.service.ExportChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ChatController {

    private final ChatService chatService;
    private final ExportChatService exportChatService;

    public ChatController(ChatService chatService, ExportChatService exportChatService) {

        this.chatService = chatService;
        this.exportChatService = exportChatService;
    }

    @PostMapping("/chat/generate")
    public String generate(@RequestBody ChatRequest chatRequest) {
        return chatService.generateResponse(chatRequest.getSessionId(),
                chatRequest.getMessage());
    }

    @GetMapping("/chat")
    public String test() {
        return "Hello World!";
    }
}
