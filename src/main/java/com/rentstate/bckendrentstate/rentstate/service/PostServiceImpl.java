package com.rentstate.bckendrentstate.rentstate.service;

import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.persistence.PostRepository;
import com.rentstate.bckendrentstate.rentstate.domain.persistence.UserRepository;
import com.rentstate.bckendrentstate.rentstate.domain.service.PostService;
import com.rentstate.bckendrentstate.rentstate.mapping.UserMapper;
import com.rentstate.bckendrentstate.shared.exeptions.ResourceNotFoundException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private static final String ENTITY = "Post";

    private final PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> getAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post getById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, postId));
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Long postId, Post newPost) {

        return postRepository.findById(postId).map(postToUpdate ->
                        postRepository.save(postToUpdate
                                .withTitle(newPost.getTitle())
                                .withCharacteristics(newPost.getCharacteristics())
                                .withLocation(newPost.getLocation())
                                .withPrice(newPost.getPrice())
                                .withImgUrl(newPost.getImgUrl())
                                .withCategory(newPost.getCategory())
                                .withDisponible(newPost.getDisponible())
                                .withUserAuthorId(newPost.getUserAuthorId())
                                .withUserRentId(newPost.getUserRentId())
                        ))


                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, postId));

    }

    @Override
    public ResponseEntity<?> delete(Long postId) {
        return postRepository.findById(postId).map(post -> {
                    postRepository.delete(post);
                    return ResponseEntity.ok().build();})
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, postId));
    }
}
