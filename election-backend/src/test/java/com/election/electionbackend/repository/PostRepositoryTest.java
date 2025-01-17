package com.election.electionbackend.repository;

import com.election.electionbackend.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void testSaveAndFindAll() {
        // Arrange
        Post post = new Post(null, "Test Title", "Test Content", 1L, "user1");
        postRepository.save(post);

        // Act
        List<Post> posts = postRepository.findAll();

        // Assert
        assertEquals(1, posts.size());
        assertEquals("Test Title", posts.get(0).getTitle());
    }

    @Test
    void testFindById() {
        // Arrange
        Post post = new Post(null, "Sample Title", "Sample Content", 2L, "user2");
        Post savedPost = postRepository.save(post);

        // Act
        Post foundPost = postRepository.findById(savedPost.getId()).orElse(null);

        // Assert
        assertNotNull(foundPost);
        assertEquals("Sample Title", foundPost.getTitle());
    }
}
