package com.rentstate.bckendrentstate.rentstate.mapping;

import com.rentstate.bckendrentstate.rentstate.domain.model.Message;
import com.rentstate.bckendrentstate.rentstate.resource.request.MessageResponse;
import com.rentstate.bckendrentstate.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class MessageMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public MessageResponse toResponse(Message message) {

        return mapper.map(message, MessageResponse.class);
    }
}
