package com.denysenko.messageservice.model;

import com.denysenko.messageservice.model.dto.NewMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "time", expression = "java( getCurrentTime() )")
    @Mapping(source = "botUserId", target = "botUserId")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "type", target = "type")
    Message newDtoToEntity(NewMessageDTO messageDTO);

    default LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

}
