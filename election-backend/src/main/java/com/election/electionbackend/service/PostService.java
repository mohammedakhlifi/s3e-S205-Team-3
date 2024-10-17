package com.election.electionbackend.service;

import com.election.electionbackend.model.Post;
import com.election.electionbackend.model.Reply;
import com.election.electionbackend.repository.PostRepository;
import com.election.electionbackend.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public PostService(PostRepository postRepository, ReplyRepository replyRepository) {
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getLatestFivePosts() {
        return postRepository.findLatestFivePosts();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Reply addReplyToPost(Long postId, Reply reply) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        reply.setPost(post);
        return replyRepository.save(reply);
    }
}
