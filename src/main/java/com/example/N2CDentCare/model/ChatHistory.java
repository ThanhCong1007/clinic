package com.example.N2CDentCare.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "ChatHistory")
public class ChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String userMessage;

    @Column(columnDefinition = "TEXT")
    private String aiResponse;

    private LocalDateTime timestamp = LocalDateTime.now();

    public ChatHistory() {}

    public ChatHistory(String userMessage, String aiResponse) {
        this.userMessage = userMessage;
        this.aiResponse = aiResponse;
    }

    // Getters & Setters
}