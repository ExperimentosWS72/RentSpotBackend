package com.rentstate.bckendrentstate.rentstate.domain.service;

import com.rentstate.bckendrentstate.rentstate.domain.model.Message;
import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    List<Post> getAll();
    Page<Post> getAll(Pageable pageable);

    Post getById(Long postId);

    Post create(Post post);

    Post update(Long postId, Post resource);

    Post updateRenter(Long postId, User renter);
    Post updateAvailable(Long postId, Boolean isAvailable);

    ResponseEntity<?> delete(Long postId);

    List<Post> getByAvailable(Boolean isAvailable);

    List<Post> getByAuthorId(Long authorId);
}

