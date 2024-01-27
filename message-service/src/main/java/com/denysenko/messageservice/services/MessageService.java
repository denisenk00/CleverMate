package com.denysenko.messageservice.services;

import com.denysenko.messageservice.model.Message;
import com.denysenko.messageservice.model.dto.AdminMessageDTO;
import com.denysenko.messageservice.model.dto.NewMessageDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface MessageService {
    Message sendToTGAndSave(@NotNull AdminMessageDTO adminMessageDTO, @NotBlank String adminUsername);

    Message save(@NotNull NewMessageDTO message);

    List<Message> getLastNMessagesByBotUserId(long botUserId, @Min(1) int amount);

    Page<Message> getPageOfMessages(long botUserId, @Min(1) int pageNumber, @Min(1) int pageSize);
}
