package com.rentstate.bckendrentstate.rentstate.service;

import com.rentstate.bckendrentstate.rentstate.domain.model.Message;
import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.persistence.MessageRepository;
import com.rentstate.bckendrentstate.rentstate.domain.service.MessageService;
import com.rentstate.bckendrentstate.rentstate.resource.request.MessageResponse;
import com.rentstate.bckendrentstate.shared.exeptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {
    private static final String ENTITY = "Post";

    private final MessageRepository messageRepository;


    public MessageServiceImpl(MessageRepository messageRepository) {

        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAll() {

        return messageRepository.findAll();
    }

    @Override
    public Page<Message> getAll(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public Message getById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, messageId));
    }

    @Override
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public ResponseEntity<?> delete(Long messageId) {
        return messageRepository.findById(messageId).map(post -> {
                    messageRepository.delete(post);
                    return ResponseEntity.ok().build();})
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, messageId));
    }

    //OTHER SERVICES

    @Override
    public List<MessageResponse> getByRecipient(User recipient){

        List<Message> messages = messageRepository.findByRecipient(recipient);

        List<MessageResponse> messageResponseList = new ArrayList<>();

        MessageResponse messageResponse;
        for (Message message : messages) {
            messageResponse = new MessageResponse();

            messageResponse.setContent(message.getContent());
            messageResponse.setId(message.getId());
            messageResponse.setAuthorName(
                    message.getAuthor().getName()+" " +message.getAuthor().getLastName()
            );
            messageResponse.setAuthorId(message.getAuthor().getId());

            messageResponseList.add(messageResponse);
        }

        return messageResponseList;
    }

}
