package com.walking.project_walking.repository;

import com.walking.project_walking.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {int countByPostId(Long postId);}
