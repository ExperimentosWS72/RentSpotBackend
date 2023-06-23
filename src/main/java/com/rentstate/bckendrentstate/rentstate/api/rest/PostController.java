package com.rentstate.bckendrentstate.rentstate.api.rest;

import com.rentstate.bckendrentstate.rentstate.domain.model.Post;
import com.rentstate.bckendrentstate.rentstate.domain.model.User;
import com.rentstate.bckendrentstate.rentstate.domain.service.PostService;
import com.rentstate.bckendrentstate.rentstate.domain.service.UserService;
import com.rentstate.bckendrentstate.rentstate.mapping.PostMapper;
import com.rentstate.bckendrentstate.rentstate.resource.request.PostRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostMapper mapper;

    public PostController(PostService postService, UserService userService, PostMapper mapper) {
        this.postService = postService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Post> getAllPost(Pageable pageable) {

        return postService.getAll();
    }

    @GetMapping("{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getById(postId);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Post>> getPostsAvailable() {
        List<Post> postsAvailable = postService.getByAvailable(true);

        if (!postsAvailable.isEmpty()) {
            return ResponseEntity.ok(postsAvailable);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Post>> getPostsByAuthorId(@PathVariable Long authorId) {

        List<Post> posts = postService.getByAuthorId(authorId);

        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody PostRequest postRequest) {

        Optional<User> authorOptional = Optional.ofNullable(userService.getById(postRequest.getAuthor_id()));

        if (authorOptional.isPresent()) {
            User author = authorOptional.get();

            Post post = mapper.toModel(postRequest);
            post.setAuthor(author);

            postService.create(post);

            return ResponseEntity.ok("{\"message\": \"Post added successfully.\"}");
        } else {
            return ResponseEntity.ok("{\"message\": \"Post not added.\"}");
        }
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


    @GetMapping("/authorclients/{author_id}")
    public ResponseEntity<List<User>> getUsersByAuthorId(@PathVariable Long author_id) {

        List<Post> posts = postService.getByAuthorId(author_id);

        List<User> usuarios = new ArrayList<>();

        for (Post post : posts) {
            User renter = post.getRenter();
            if (renter != null) {
                usuarios.add(renter);
            }
        }

        if (!usuarios.isEmpty()) {
            return ResponseEntity.ok(usuarios);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{postId}/renter")
    public ResponseEntity<String> updateRenter(@PathVariable Long postId, @RequestBody Long renterId) {

        Optional<User> userOptional = Optional.ofNullable(userService.getById(renterId));

        if (userOptional.isPresent()) {

            postService.updateRenter(postId, userOptional.get());

            return ResponseEntity.ok("Renter upgraded successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/available/{postId}")
    public ResponseEntity<String> updateAvailable(@PathVariable Long postId, @RequestBody Boolean isAvailable) {

        if (postService.updateAvailable(postId, isAvailable) !=null ){
            return ResponseEntity.ok("Availability updated successfully.");
        }else{
            return ResponseEntity.notFound().build();
        }

    }


}
