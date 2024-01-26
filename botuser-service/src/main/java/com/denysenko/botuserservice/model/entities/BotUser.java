package com.denysenko.botuserservice.model.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("botusers")
public class BotUser {
    @Id
    private Long id;
    private String name;
    private boolean active;
}
