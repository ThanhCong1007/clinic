package com.example.N2CDentCare.controller;

import com.example.N2CDentCare.Service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody Map<String, String> request, HttpSession session) {
        String userMessage = request.get("message");
        String conversationId = request.get("conversationId");

        if (userMessage == null || userMessage.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Message is required"));
        }

        // Nếu là cuộc trò chuyện mới (được khởi tạo khi mở tab mới)
        if ("new".equals(conversationId)) {
            session.removeAttribute("appointmentState");
            session.removeAttribute("appointmentDate");
            session.removeAttribute("appointmentTime");
            session.removeAttribute("appointmentService");
            session.removeAttribute("appointmentPhone");
            session.removeAttribute("appointmentDebug");
            session.removeAttribute("hasGreeted");
            session.removeAttribute("chatHistory");
        }

        String aiResponse = chatService.chatWithAI(userMessage, session);
        return ResponseEntity.ok(Map.of("response", aiResponse));
    }
}