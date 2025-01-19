package com.election.electionbackend.repository;

import com.election.electionbackend.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void testSavePost() {
        // Arrange
        Post post = new Post(1L, "Test Title", "Test Content", 1L, "User1");

        // Act
        Post savedPost = postRepository.save(post);

        // Assert
        assertEquals("Test Title", savedPost.getTitle());
        assertEquals("Test Content", savedPost.getContent());
    }

    @Test
    void testFindAllPosts() {
        // Arrange
        postRepository.save(new Post(1L, "Title 1", "Content 1", 1L, "User1"));
        postRepository.save(new Post(2L, "Title 2", "Content 2", 1L, "User2"));

        // Act
        List<Post> posts = postRepository.findAll();

        // Assert
        assertEquals(2, posts.size());
        assertEquals("Title 1", posts.get(0).getTitle());
        assertEquals("Title 2", posts.get(1).getTitle());
    }
}
