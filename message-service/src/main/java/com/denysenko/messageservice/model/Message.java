package com.denysenko.messageservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("messages")
public class Message {
    @Id
    private String id;
    private Long botUserId;
    private String text;
    private LocalDateTime time;
    private MessageType type;
    private String adminUsername;

    public Optional<String> getAdminUsername() {
        return Optional.ofNullable(adminUsername);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(Long botUserId) {
        this.botUserId = botUserId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
}
