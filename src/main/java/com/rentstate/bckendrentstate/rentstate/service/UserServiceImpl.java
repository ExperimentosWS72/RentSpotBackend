package com.rentstate.bckendrentstate.rentstate.service;

import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.persistence.UserRepository;
import com.rentstate.bckendrentstate.rentstate.domain.service.UserService;
import com.rentstate.bckendrentstate.rentstate.mapping.UserMapper;
import com.rentstate.bckendrentstate.shared.exeptions.ResourceNotFoundException;
import com.rentstate.bckendrentstate.shared.exeptions.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final String ENTITY = "User";

    private final UserRepository userRepository;
    private final UserMapper mapper;

    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, Validator validator) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User newUser) {

        return userRepository.findById(userId).map(userToUpdate ->
                        userRepository.save(userToUpdate
                                .withName(newUser.getName())
                                .withLastName(newUser.getLastName())
                                .withAge(newUser.getAge())
                                .withEmail(newUser.getEmail())
                                .withPassword(newUser.getPassword())
                                .withGender(newUser.getGender())
                                .withDescription(newUser.getDescription())
                        ))


                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));

    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(student -> {
                    userRepository.delete(student);
                    return ResponseEntity.ok().build();})
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
