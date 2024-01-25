package com.denysenko.botuserservice.model;

import com.denysenko.botuserservice.model.dtos.BotUserDTO;
import com.denysenko.botuserservice.model.entities.BotUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BotUserMapper {

    BotUserDTO entityToDTO(BotUser botUser);
    List<BotUserDTO> entitiesToDTOs(List<BotUser> botUsers);
    BotUser dtoToEntity(BotUserDTO botUserDTO);

}
