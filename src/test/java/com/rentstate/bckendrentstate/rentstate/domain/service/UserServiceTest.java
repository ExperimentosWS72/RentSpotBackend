package com.rentstate.bckendrentstate.rentstate.domain.service;

import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.persistence.UserRepository;
import com.rentstate.bckendrentstate.rentstate.service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User user1;
    @BeforeEach
    void setUp(){
        user1 = User.builder()
                .id(1L)
                .name("Mario")
                .lastName("Casas")
                .email("m@email.com")
                .password("m123")
                .age(20)
                .gender("male")
                .description("actor")
                .rankPoints(4.0f)
                .build();
    }
    @DisplayName("Test for create new user")
    @Test
    void testCreateUser(){

        when(userRepository.save(user1)).thenReturn(user1);
        User save=userService.create(user1);
        assertThat(save).isEqualTo(user1);

    }
    @DisplayName("Test for get user by ID")
    @Test
    void testGetUserById() {
        given(userRepository.findById(user1.getId())).willReturn(Optional.of(user1));
        User retrievedUser = userService.getById(user1.getId());
        assertEquals(user1, retrievedUser);
    }

    @DisplayName("Test for update user resource")
    @Test
    void testUpdateUser(){
        given(userRepository.save(any(User.class))).willReturn(user1);
        given(userRepository.findById(user1.getId())).willReturn(Optional.of(user1));

        User userSave = userRepository.findById(user1.getId()).get();
        userSave.setEmail("mario@emil.com");
        userSave.setName("Marito");
        userSave.setLastName("Casitas");

        User userUpdate = userRepository.save(userSave);

        assertThat(userUpdate.getEmail()).isEqualTo("mario@emil.com");
        assertThat(userUpdate.getName()).isEqualTo("Marito");
        assertThat(userUpdate.getLastName()).isEqualTo("Casitas");

    }

    @DisplayName("Test for delete user")
    @Test
    void testDeleteUser(){
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        ResponseEntity<?> result = userService.delete(user1.getId());

        assertThat(result).isEqualTo(ResponseEntity.ok().build());
    }

    @DisplayName("Test for user list")
    @Test
    void testGetAll(){
        List<User> userList= new ArrayList<>();
        userList.add(user1);
        when(userRepository.findAll()).thenReturn(userList);
        List<User>users=userService.getAll();
        assertThat(users).isEqualTo(userList);
    }

}
