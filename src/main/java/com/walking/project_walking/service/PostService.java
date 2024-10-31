package com.walking.project_walking.service;

import com.walking.project_walking.domain.Posts;
import com.walking.project_walking.domain.dto.PostRequest;
import com.walking.project_walking.domain.dto.PostResponse;
import com.walking.project_walking.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class PostService {

    @Autowired
    private PostRepository postRepository;


    public PostResponse savePost(PostRequest postRequest) {
        Posts post = Posts.builder()
                .userId(postRequest.getUserId())
                .boardId(postRequest.getBoardId())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postImage(postRequest.getPostImage())
                .build();

        Posts savedPost = postRepository.save(post);

        return new PostResponse(
                savedPost.getPostId(),
                savedPost.getUserId(),
                savedPost.getBoardId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getPostImage(),
                savedPost.getViewCount(),
                savedPost.getCreatedAt(),
                savedPost.getModifiedAt(),
                savedPost.getIsDeleted()
        );
    }


    public int getViewCount(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."))
                .getViewCount();
    }


    @Transactional
    public void updateViewCount(Long postId) {
        postRepository.incrementViewCount(postId);
    }


    public PostResponse modifyPost(Long postId, PostRequest postRequest) {
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setPostImage(postRequest.getPostImage());

        Posts updatedPost = postRepository.save(post);

        return new PostResponse(
                updatedPost.getPostId(),
                updatedPost.getUserId(),
                updatedPost.getBoardId(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.getPostImage(),
                updatedPost.getViewCount(),
                updatedPost.getCreatedAt(),
                updatedPost.getModifiedAt(),
                updatedPost.getIsDeleted()
        );
    }


    public void deletePost(Long postId) {
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.setIsDeleted(true);
        postRepository.save(post);


    }
}