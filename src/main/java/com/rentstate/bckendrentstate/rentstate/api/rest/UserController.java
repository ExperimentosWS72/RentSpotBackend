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

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;

        this.mapper = mapper;
    }

    @GetMapping
    public List<User> getAllUsers() {
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

}
