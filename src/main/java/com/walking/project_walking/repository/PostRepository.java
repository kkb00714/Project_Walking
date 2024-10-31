package com.walking.project_walking.repository;


import com.walking.project_walking.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface PostRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p.viewCount FROM Posts p WHERE p.postId = :postId")
    int getViewCountByPostId(Long postId);

    @Modifying
    @Transactional
    @Query("UPDATE Posts p SET p.viewCount = p.viewCount + 1 WHERE p.postId = :postId")
    void incrementViewCount(Long postId);
}
