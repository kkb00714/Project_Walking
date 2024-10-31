package com.walking.project_walking.controller;


import com.walking.project_walking.domain.dto.AddCommentRequest;
import com.walking.project_walking.domain.dto.CommentResponse;
import com.walking.project_walking.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @PostMapping("/{commentId}")
    public ResponseEntity<CommentResponse> createComment (@Valid @RequestBody AddCommentRequest dto) {
        CommentResponse createdDto = commentService.createComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status (HttpStatus.OK).body ("댓글이 삭제되었습니다");
    }


    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> modifyComment(@PathVariable Long commentId, @Valid @RequestBody AddCommentRequest dto) {
        CommentResponse updatedComment = commentService.modifyComment(commentId, dto);
        return ResponseEntity.status (HttpStatus.OK).body (updatedComment);
    }


    @GetMapping("/{postId}/comment-count")
    public ResponseEntity<Integer> getCommentCount(@RequestBody Long postId) {
        int count = commentService.getCommentCount(postId);
        return ResponseEntity.status (HttpStatus.OK).body(count);
    }

}