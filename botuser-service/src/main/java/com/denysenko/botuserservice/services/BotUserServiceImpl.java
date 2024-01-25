package com.denysenko.botuserservice.services;

import com.denysenko.botuserservice.exceptions.DocumentNotFoundException;
import com.denysenko.botuserservice.model.BotUserMapper;
import com.denysenko.botuserservice.model.dtos.BotUserDTO;
import com.denysenko.botuserservice.model.entities.BotUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BotUserServiceImpl implements BotUserService{

    private final MongoTemplate mongoOperations;
    private final BotUserMapper mapper;

    @Override
    public void changeProfileStatus(Long chatId, boolean isActive) {
        var query = new Query(Criteria.where("chatId").is(chatId));
        var res = mongoOperations.updateFirst(query, Update.update("active", isActive), BotUser.class);
        if(res.getModifiedCount() < 1)
            throw new DocumentNotFoundException("Bot user with chatId = " + chatId + " doesn't exist");
    }

    @Override
    public BotUserDTO saveOrUpdate(BotUserDTO botUserDTO) {
        var entity = mapper.dtoToEntity(botUserDTO);
        var saved = mongoOperations.save(entity);
        return mapper.entityToDTO(saved);
    }

    // todo: TEST method
    @Override
    public Page<BotUserDTO> getPageOfBotUsers(int pageNumber, int pageSize) {
        if(pageNumber < 1 || pageSize < 1)
            throw new IllegalArgumentException("Page and size arguments should be greater than zero");

        var pageable = PageRequest.of(pageNumber - 1, pageSize - 1);
        var query = new Query().with(pageable);
        //mongoOperations.count(query.skip(-1).limit(-1), YourObjectType.class)
        var botUserList = List.of(new BotUser());//mongoOperations.find(query, BotUser.class);
        return new PageImpl(mapper.entitiesToDTOs(botUserList), pageable, botUserList.size());
    }
}
