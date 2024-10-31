package com.walking.project_walking.controller;

import com.walking.project_walking.domain.dto.PostRequest;
import com.walking.project_walking.domain.dto.PostResponse;
import com.walking.project_walking.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")

public class PostController {
    @Autowired
    private PostService postService;


    @PostMapping
    public ResponseEntity<PostResponse> savePosts(@Valid @RequestBody PostRequest postRequest) {
        PostResponse savedPost = postService.savePost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }


    @GetMapping("/{postId}/view-count")
    public ResponseEntity<Integer> getViewCount(@PathVariable Long postId) {
        int viewCount = postService.getViewCount(postId);
        return ResponseEntity.ok(viewCount);
    }


    @PutMapping("/{postId}/view-count")
    public ResponseEntity<String> updateViewCount(@PathVariable Long postId) {
        postService.updateViewCount(postId);
        return ResponseEntity.ok("조회수가 업데이트되었습니다.");
    }


    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> modifyPosts(@PathVariable Long postId, @Valid @RequestBody PostRequest postRequest) {
        PostResponse updatedPost = postService.modifyPost(postId, postRequest);
        return ResponseEntity.ok(updatedPost);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePosts(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");


    }
}
