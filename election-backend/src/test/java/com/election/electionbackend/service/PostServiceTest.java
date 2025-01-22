package com.election.electionbackend.service;

import com.election.electionbackend.model.Post;
import com.election.electionbackend.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

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
        Post post = new Post(1L, "Test Title", "Test Content", 1L, "User1");
        when(postRepository.save(post)).thenReturn(post);

        // Act
        Post result = postService.createPost(post);

        // Assert
        assertEquals("Test Title", result.getTitle());
        verify(postRepository, times(1)).save(post);
    }
}
