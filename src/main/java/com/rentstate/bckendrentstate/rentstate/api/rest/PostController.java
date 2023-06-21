package com.rentstate.bckendrentstate.rentstate.api.rest;

import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.service.PostService;
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
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPost(Pageable pageable) {

        return postService.getAll();
    }

    @GetMapping("{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody Post resource) {
        return postService.create(resource);
    }

    @PutMapping("{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post resource) {
        return postService.update(postId,resource);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postService.delete(postId);
    }

    //EXTRA METHODOS
    @GetMapping("/available")
    public List<Post> getPostAvailable(Pageable pageable) {
        List<Post> allPosts = postService.getAll();
        List<Post> availablePosts = new ArrayList<>();

        for (Post post : allPosts) {
            if (post.getAvailable()) {
                availablePosts.add(post);
            }
        }

        return availablePosts;
    }


}
