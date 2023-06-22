package com.rentstate.bckendrentstate.rentstate.api.rest;

import com.rentstate.bckendrentstate.rentstate.domain.model.Message;
import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.service.MessageService;
import com.rentstate.bckendrentstate.rentstate.domain.service.UserService;
import com.rentstate.bckendrentstate.rentstate.mapping.UserMapper;
import com.rentstate.bckendrentstate.rentstate.resource.CreateUserResource;
import com.rentstate.bckendrentstate.rentstate.resource.UpdateUserResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final MessageService messageService;
    private final UserMapper mapper;

    public UserController(UserService userService, MessageService messageService, UserMapper mapper) {
        this.userService = userService;
        this.messageService = messageService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<User> getAllUsers(Pageable pageable) {
        return  userService.getAll();
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getById(userId);
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserResource resource) {
       return userService.create(mapper.toModel(resource));
    }

    @PutMapping("{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        return userService.update(userId,mapper.toModel(resource));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }



    //EXTRA METHODOS

    @PostMapping("/{userId}/clients")
    public ResponseEntity<String> addClientToUser(@PathVariable Long userId, @RequestBody int clientId) {
        User user = userService.getById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.addClient(clientId);
        userService.update(userId, user);

        return ResponseEntity.ok("Client add succesfully");
    }

    @PostMapping("/{userId}/posts")
    public ResponseEntity<String> addPostToUser(@PathVariable Long userId, @RequestBody int postId) {
        User user = userService.getById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.addPost(postId);
        userService.update(userId, user);

        return ResponseEntity.ok("Post add succesfully");
    }

    @PostMapping("/{userId}/messages")
    public ResponseEntity<String> addMessageToUser(@PathVariable Long userId, @RequestBody int messageId) {
        User user = userService.getById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.addMessage(messageId);
        userService.update(userId, user);

        return ResponseEntity.ok("Message add succesfully");
    }

    @GetMapping("/{userId}/getMessages")
    public List<Message> getAllMessageByUserId(@PathVariable Long userId) {

        List<Integer> listIdMessages = userService.getById(userId).getListMessages();

        List<Message> messagesList = new ArrayList<>();

        for(Integer i : listIdMessages){
            messagesList.add(messageService.getById(Long.valueOf(i)));
        }
        return messagesList;
    }

}
