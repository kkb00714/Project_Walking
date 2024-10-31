package com.walking.project_walking.controller;


import com.walking.project_walking.domain.dto.CommentRequest;
import com.walking.project_walking.domain.dto.CommentResponse;
import com.walking.project_walking.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId, @Valid @RequestBody CommentRequest dto) {
        dto.setPostId(postId);
        CommentResponse createdDto = commentService.createComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }


    @DeleteMapping("/{postId}/comments")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId) {
        commentService.deleteComment(postId);
        return ResponseEntity.status (HttpStatus.OK).body ("댓글이 삭제되었습니다");
    }


    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> modifyComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequest dto) {
        CommentResponse updatedComment = commentService.modifyComment(commentId, dto);
        return ResponseEntity.status (HttpStatus.OK).body (updatedComment);
    }


    @GetMapping("/{commentId}/count")
    public ResponseEntity<Integer> getCommentCount(@PathVariable Long commentId) {
        int count = commentService.getCommentCount(commentId);
        return ResponseEntity.status (HttpStatus.OK).body(count);
    }


    @PostMapping("/{commentId}/replies")
    public ResponseEntity<CommentResponse> saveReply(@PathVariable Long commentId, @Valid @RequestBody CommentRequest dto) {
        dto.setParentCommentId(commentId);
        CommentResponse createdReply = commentService.createComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReply);
    }


    @DeleteMapping("/{commentId}/replies/{replyId}")
    public ResponseEntity<String> deleteReply(@PathVariable Long commentId, @PathVariable Long replyId) {
        commentService.deleteComment(replyId);
        return ResponseEntity.status(HttpStatus.OK).body("답글이 삭제되었습니다");
    }


    @PutMapping("/{commentId}/replies/{replyId}")
    public ResponseEntity<CommentResponse> modifyReply(@PathVariable Long replyId, @Valid @RequestBody CommentRequest dto) {
        CommentResponse updatedReply = commentService.modifyComment(replyId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReply);
    }


}