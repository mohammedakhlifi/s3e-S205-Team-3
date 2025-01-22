package com.election.electionbackend.controller;

import com.election.electionbackend.model.Post;
import com.election.electionbackend.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLatestFiveTopics() {
        // Arrange
        List<Post> mockPosts = Collections.singletonList(new Post(1L, "Test Topic", "Test Content"));
        when(postService.getLatestFiveTopics()).thenReturn(mockPosts);

        // Act
        List<Post> topics = postController.getLatestFiveTopics();

        // Assert
        assertEquals(1, topics.size());
        assertEquals("Test Topic", topics.get(0).getTitle());
    }

    @Test
    void testCreatePost() {
        // Arrange
        Post mockPost = new Post(1L, "New Post", "New Content");
        when(postService.createPost(mockPost)).thenReturn(mockPost);

        // Act
        ResponseEntity<Post> response = postController.createPost(mockPost);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mockPost, response.getBody());
    }
}
