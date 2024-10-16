package com.election.electionbackend.controller;

import com.election.electionbackend.model.Post;
import com.election.electionbackend.model.Reply;
import com.election.electionbackend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Fetch all posts with replies
    @GetMapping("/topics")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/topics/latest")
    public List<Post> getLatestFivePosts() {
        return postService.getLatestFivePosts();
    }


    // Create a new post
    @PostMapping("/topics")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // Add a reply to a post
    @PostMapping("/topics/{postId}/replies")
    public ResponseEntity<Reply> addReply(@PathVariable Long postId, @RequestBody Reply reply) {
        Reply createdReply = postService.addReplyToPost(postId, reply);
        return new ResponseEntity<>(createdReply, HttpStatus.CREATED);
    }
}
