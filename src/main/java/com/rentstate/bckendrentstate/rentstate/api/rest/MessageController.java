package com.rentstate.bckendrentstate.rentstate.api.rest;

import com.rentstate.bckendrentstate.rentstate.domain.model.Message;
import com.rentstate.bckendrentstate.rentstate.domain.service.MessageService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {

        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllMessage(Pageable pageable) {

        return messageService.getAll();
    }

    @GetMapping("{messageId}")
    public Message getMessageById(@PathVariable Long messageId) {
        return messageService.getById(messageId);
    }

    @PostMapping
    public Message createMessage(@RequestBody Message resource) {
        return messageService.create(resource);
    }

    @DeleteMapping("{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId) {

        return messageService.delete(messageId);
    }

}
