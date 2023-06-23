package com.rentstate.bckendrentstate.rentstate.api.rest;

import com.rentstate.bckendrentstate.rentstate.domain.model.Message;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.service.MessageService;
import com.rentstate.bckendrentstate.rentstate.domain.service.UserService;
import com.rentstate.bckendrentstate.rentstate.mapping.MessageMapper;
import com.rentstate.bckendrentstate.rentstate.resource.request.MensajeRequest;
import com.rentstate.bckendrentstate.rentstate.resource.request.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final MessageMapper mapper;

    public MessageController(MessageService messageService, UserService userService, MessageMapper mapper) {

        this.messageService = messageService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Message> getAllMessage() {
        return messageService.getAll();
    }

    @GetMapping("{messageId}")
    public MessageResponse getMessageById(@PathVariable Long messageId) {

        Message message = messageService.getById(messageId);
        MessageResponse messageResponse =  mapper.toResponse(message);

        messageResponse.setAuthorName(message.getAuthor().getName() +" "+ message.getAuthor().getLastName());

        return messageResponse;
    }

    @PostMapping
    public ResponseEntity<String> addMessage(@RequestBody MensajeRequest messageRequest) {

        Optional<User> authorOptional = Optional.ofNullable(userService.getById(messageRequest.getAuthorId()));
        Optional<User> recipientOptional = Optional.ofNullable(userService.getById(messageRequest.getRecipientId()));

        if (authorOptional.isPresent() && recipientOptional.isPresent()) {
            User author = authorOptional.get();
            User recipient = recipientOptional.get();


            Message message = new Message();
            message .setContent(messageRequest.getContent());
            message .setAuthor(author);
            message .setRecipient(recipient);

            messageService.create(message);

            return ResponseEntity.ok("Message added successfully.");
        } else {
            return ResponseEntity.badRequest().body("error to added message");
        }
    }
    @DeleteMapping("{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId) {

        return messageService.delete(messageId);

    }

    //OTHER APIS

    //Devuelve lista de mensajes dado IdRecipient
    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<List<MessageResponse>> getMessageByRecipientId(@PathVariable Long recipientId) {

        Optional<User> recipientOptional = Optional.ofNullable(userService.getById(recipientId));

        if (recipientOptional.isPresent()) {
            User recipient = recipientOptional.get();

            List<MessageResponse> messages = messageService.getByRecipient(recipient);

            return ResponseEntity.ok(messages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

