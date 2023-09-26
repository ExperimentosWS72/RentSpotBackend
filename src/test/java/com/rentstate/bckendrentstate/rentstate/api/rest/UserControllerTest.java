package com.rentstate.bckendrentstate.rentstate.api.rest;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.service.UserService;
import com.rentstate.bckendrentstate.rentstate.mapping.UserMapper;
import com.rentstate.bckendrentstate.rentstate.resource.CreateUserResource;
import com.rentstate.bckendrentstate.rentstate.resource.UpdateUserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;

    private User user2;
    @BeforeEach
    void setUp(){
        user2 = User.builder()
                .id(1L)
                .name("Mario")
                .lastName("Casas")
                .email("m@email.com")
                .password("m123")
                .age(18)
                .gender("")
                .description("")
                .rankPoints(5.0f)
                .build();
    }
    @Test
    void testCreateUser() throws Exception {
        CreateUserResource createUserResource = new CreateUserResource();
        createUserResource.setName("Marito");
        createUserResource.setLastName("Casitas");
        createUserResource.setEmail("ma@email.com");
        createUserResource.setPassword("m1234");

        when(userService.create(any(User.class))).thenReturn(user2);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserResource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(user2.getName()))
                .andExpect(jsonPath("$.lastName").value(user2.getLastName()))
                .andExpect(jsonPath("$.email").value(user2.getEmail()))
                .andExpect(jsonPath("$.password").value(user2.getPassword()));
    }

    @Test
    void testGetUserById() throws Exception {

        when(userService.getById(user2.getId())).thenReturn(user2);

        mockMvc.perform(get("/api/users/{userId}", user2.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(user2.getName()))
                .andExpect(jsonPath("$.lastName").value(user2.getLastName()))
                .andExpect(jsonPath("$.email").value(user2.getEmail()))
                .andExpect(jsonPath("$.password").value(user2.getPassword()));
    }

    @Test
    void testUpdateUser() throws Exception {
        Long userId = 1L;
        UpdateUserResource updateUserResource = new UpdateUserResource();
        updateUserResource.setName("NuevoNombre");
        updateUserResource.setLastName("NuevoApellido");
        updateUserResource.setEmail("nuevo@email.com");
        updateUserResource.setPassword("nuevaContraseña");

        when(userService.update(eq(userId), any(User.class))).thenReturn(user2);

        mockMvc.perform(put("/api/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateUserResource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(user2.getName()))
                .andExpect(jsonPath("$.lastName").value(user2.getLastName()))
                .andExpect(jsonPath("$.email").value(user2.getEmail()))
                .andExpect(jsonPath("$.password").value(user2.getPassword()));
    }

    @Test
    void testDeleteUser() throws Exception {

        when(userService.delete(user2.getId())).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/api/users/{userId}", user2.getId()))
                .andExpect(status().isOk());
    }


}
