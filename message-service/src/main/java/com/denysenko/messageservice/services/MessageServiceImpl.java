package com.denysenko.messageservice.services;

import com.denysenko.messageservice.model.Message;
import com.denysenko.messageservice.model.MessageMapper;
import com.denysenko.messageservice.model.MessageType;
import com.denysenko.messageservice.model.dto.AdminMessageDTO;
import com.denysenko.messageservice.model.dto.NewMessageDTO;
import com.denysenko.messageservice.rabbitmq.ResponseDTO;
import com.denysenko.messageservice.rabbitmq.ResponseProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MongoTemplate mongoTemplate;
    private final ResponseProducer responseProducer;
    private final MessageMapper messageMapper;

    @Override
    public Message sendToTGAndSave(AdminMessageDTO adminMessageDTO, String adminUsername) {
        var responseDTO = new ResponseDTO(adminMessageDTO.botUserId(), adminMessageDTO.text());
        responseProducer.sendResponse(responseDTO);
        var messageEntity = Message.builder()
            .type(MessageType.RESPONSE)
            .adminUsername(adminUsername)
            .botUserId(adminMessageDTO.botUserId())
            .text(adminMessageDTO.text())
            .time(LocalDateTime.now())
            .build();
        return mongoTemplate.save(messageEntity);
    }

    @Override
    public Message save(NewMessageDTO message) {
        var entity = messageMapper.newDtoToEntity(message);
        return mongoTemplate.save(entity);
    }

    @Override
    public List<Message> getLastNMessagesByBotUserId(long botUserId, int amount) {
        var query = new Query(Criteria.where("botUserId").is(botUserId))
                .with(Sort.by("time").descending()).limit(amount);
        return mongoTemplate.find(query, Message.class);
    }

    @Override
    public Page<Message> getPageOfMessages(long botUserId, int pageNumber, int pageSize) {
        var sort = Sort.by(Sort.Direction.DESC, "time");
        var pageable = PageRequest.of(pageNumber - 1, pageSize - 1, sort);
        var query = new Query(Criteria.where("botUserId").is(botUserId)).with(pageable);
        var messages = mongoTemplate.find(query, Message.class);
        return new PageImpl(messages, pageable, messages.size());
    }
}
