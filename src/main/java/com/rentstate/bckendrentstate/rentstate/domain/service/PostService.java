package com.rentstate.bckendrentstate.rentstate.domain.service;

import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
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

    ResponseEntity<?> delete(Long postId);
}
