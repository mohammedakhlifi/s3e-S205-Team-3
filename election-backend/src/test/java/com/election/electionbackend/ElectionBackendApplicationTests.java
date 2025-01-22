package com.election.electionbackend;

import com.election.electionbackend.model.Post;
import com.election.electionbackend.model.Reply;
import com.election.electionbackend.model.User;
import com.election.electionbackend.repository.PostRepository;
import com.election.electionbackend.repository.ReplyRepository;
import com.election.electionbackend.repository.UserRepository;
import com.election.electionbackend.service.PostService;
import com.election.electionbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ElectionBackendApplicationTests {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@MockBean
	private PostRepository postRepository;

	@MockBean
	private ReplyRepository replyRepository;

	@MockBean
	private UserRepository userRepository;

	private Post post;
	private Reply reply;
	private User user;

	@BeforeEach
	void setUp() {
		post = new Post(1L, "Test Title", "Test Content", 1L, "Test User");
		reply = new Reply();
		reply.setContent("Test Reply");
		reply.setPost(post);

		user = new User();
		user.setId(1L);
		user.setName("Test User");
		user.setEmail("test@example.com");
	}

	@Test
	void contextLoads() {
		assertNotNull(postService);
		assertNotNull(userService);
	}

	// Tests for PostService
	@Test
	void testAddReplyToPost() {
		when(postRepository.findById(1L)).thenReturn(Optional.of(post));
		when(replyRepository.save(reply)).thenReturn(reply);

		Reply result = postService.addReplyToPost(1L, reply);

		assertEquals("Test Reply", result.getContent());
		verify(replyRepository, times(1)).save(reply);
	}

	@Test
	void testAddReplyToPost_PostNotFound() {
		when(postRepository.findById(1L)).thenReturn(Optional.empty());

		RuntimeException exception = assertThrows(RuntimeException.class, () -> postService.addReplyToPost(1L, reply));
		assertEquals("Post niet gevonden", exception.getMessage());
		verify(replyRepository, never()).save(any());
	}

	// Tests for UserService
	@Test
	void testSaveUser() {
		when(userRepository.save(user)).thenReturn(user);
		userService.saveUser(user);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testFindUserByEmail() {
		when(userRepository.findByEmail("test@example.com")).thenReturn(user);
		User result = userService.findByEmail("test@example.com");
		assertEquals("Test User", result.getName());
		verify(userRepository, times(1)).findByEmail("test@example.com");
	}

	@Test
	void testFindUserByName() {
		when(userRepository.findByName("Test User")).thenReturn(user);
		User result = userService.findByName("Test User");
		assertEquals("test@example.com", result.getEmail());
		verify(userRepository, times(1)).findByName("Test User");
	}
}
