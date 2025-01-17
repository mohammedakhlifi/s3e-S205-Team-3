package com.election.electionbackend.service;

import com.election.electionbackend.model.Post;
import com.election.electionbackend.model.Reply;
import com.election.electionbackend.repository.PostRepository;
import com.election.electionbackend.repository.ReplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    private PostRepository postRepository;
    private ReplyRepository replyRepository;
    private PostService postService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        replyRepository = mock(ReplyRepository.class);
        postService = new PostService(postRepository, replyRepository);
    }

    @Test
    void testGetTopicsWithPagination() {
        // Arrange
        Post post1 = new Post(1L, "Title 1", "Content 1", 1L, "User1");
        Post post2 = new Post(2L, "Title 2", "Content 2", 1L, "User2");

        List<Post> posts = Arrays.asList(post1, post2);
        Page<Post> page = new PageImpl<>(posts);
        when(postRepository.findAll(PageRequest.of(0, 2, Sort.by("createdAt").descending()))).thenReturn(page);

        // Act
        Page<Post> result = postService.getTopicsWithPagination(0, 2);

        // Assert
        assertEquals(2, result.getContent().size());
        verify(postRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void testGetLatestFiveTopics() {
        // Arrange
        Post post1 = new Post(1L, "Title 1", "Content 1", 1L, "User1");
        Post post2 = new Post(2L, "Title 2", "Content 2", 1L, "User2");

        List<Post> posts = Arrays.asList(post1, post2);
        when(postRepository.findAll(PageRequest.of(0, 5, Sort.by("createdAt").descending())))
                .thenReturn(new PageImpl<>(posts));

        // Act
        List<Post> result = postService.getLatestFiveTopics();

        // Assert
        assertEquals(2, result.size());
        verify(postRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void testCreatePost() {
        // Arrange
        Post post = new Post(null, "Title", "Content", 1L, "User1");
        when(postRepository.save(post)).thenReturn(post);

        // Act
        Post result = postService.createPost(post);

        // Assert
        assertNotNull(result);
        assertEquals("Title", result.getTitle());
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void testAddReplyToPost() {
        // Arrange
        Post post = new Post(1L, "Title", "Content", 1L, "User1");
        Reply reply = new Reply();
        reply.setContent("Test Reply");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(replyRepository.save(reply)).thenReturn(reply);

        // Act
        Reply result = postService.addReplyToPost(1L, reply);

        // Assert
        assertEquals("Test Reply", result.getContent());
        assertEquals(post, reply.getPost());
        verify(postRepository, times(1)).findById(1L);
        verify(replyRepository, times(1)).save(reply);
    }

    @Test
    void testAddReplyToPost_PostNotFound() {
        // Arrange
        Reply reply = new Reply();
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> postService.addReplyToPost(1L, reply));
        assertEquals("Post niet gevonden", exception.getMessage());
        verify(replyRepository, never()).save(any());
    }
}
