package com.denysenko.botuserservice.services;

import com.denysenko.botuserservice.model.dtos.BotUserDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BotUserService {
    void changeProfileStatus(@NotNull Long chatId, boolean isActive);

    BotUserDTO saveOrUpdate(BotUserDTO botUser);

    Page<BotUserDTO> getPageOfBotUsers(int pageNumber, int pageSize);
}
