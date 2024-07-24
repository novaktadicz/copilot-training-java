package com.zuehlke.chat.controller;

import com.zuehlke.chat.dto.ChatRequest;
import com.zuehlke.chat.service.ChatService;
import com.zuehlke.chat.service.ChatExportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;
    private final ChatExportService chatExportService;

    public ChatController(ChatService chatService, ChatExportService chatExportService) {

        this.chatService = chatService;
        this.chatExportService = chatExportService;
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
