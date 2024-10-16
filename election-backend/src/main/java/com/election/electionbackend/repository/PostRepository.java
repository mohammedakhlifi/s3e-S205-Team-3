package com.election.electionbackend.repository;

import com.election.electionbackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM Post ORDER BY CREATED_AT DESC LIMIT 5", nativeQuery = true)
    List<Post> findLatestFivePosts();
}
