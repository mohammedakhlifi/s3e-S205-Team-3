package com.election.electionbackend.repository;

import com.election.electionbackend.model.Reply;
import com.election.electionbackend.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    void testSaveAndFindAll() {
        // Arrange
        Reply reply = new Reply();
        reply.setContent("Test Reply");
        replyRepository.save(reply);

        // Act
        List<Reply> replies = replyRepository.findAll();

        // Assert
        assertEquals(1, replies.size());
        assertEquals("Test Reply", replies.get(0).getContent());
    }

    @Test
    void testFindById() {
        // Arrange
        Reply reply = new Reply();
        reply.setContent("Another Reply");
        Reply savedReply = replyRepository.save(reply);

        // Act
        Reply foundReply = replyRepository.findById(savedReply.getId()).orElse(null);

        // Assert
        assertNotNull(foundReply);
        assertEquals("Another Reply", foundReply.getContent());
    }
}
