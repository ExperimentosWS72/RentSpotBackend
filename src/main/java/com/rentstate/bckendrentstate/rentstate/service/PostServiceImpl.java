package com.rentstate.bckendrentstate.rentstate.service;

import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.persistence.PostRepository;
import com.rentstate.bckendrentstate.rentstate.domain.service.PostService;
import com.rentstate.bckendrentstate.rentstate.domain.service.UserService;
import com.rentstate.bckendrentstate.shared.exeptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private static final String ENTITY = "Post";

    private final PostRepository postRepository;
    private final UserService userService;


    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
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
                                .withDescription(newPost.getDescription())
                                .withCharacteristics(newPost.getCharacteristics())
                                .withLocation(newPost.getLocation())
                                .withPrice(newPost.getPrice())
                                .withImgUrl(newPost.getImgUrl())
                                .withCategory(newPost.getCategory())
                        ))

                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, postId));

    }

    @Override
    public Post updateRenter(Long postId, User renter) {
        return postRepository.findById(postId).map(postToUpdate ->
                        postRepository.save(postToUpdate
                                .withRenter(renter)
                        ))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, postId));
    }

    @Override
    public Post updateAvailable(Long postId, Boolean isAvailable) {
        return postRepository.findById(postId).map(postToUpdate ->
                        postRepository.save(postToUpdate
                                .withAvailable(isAvailable)
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



    //OTHER APIS
    public List<Post> getByAvailable(Boolean isAvailable){
        return postRepository.findByAvailable(isAvailable);
    }

    @Override
    public List<Post> getByAuthorId(Long authorId) {

        return postRepository.findByAuthor(userService.getById(authorId));
    }
}
