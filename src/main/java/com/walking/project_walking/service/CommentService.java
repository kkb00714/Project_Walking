package com.walking.project_walking.service;

import lombok.*;
import com.walking.project_walking.domain.Comments;
import com.walking.project_walking.domain.dto.CommentRequest;
import com.walking.project_walking.domain.dto.CommentResponse;
import com.walking.project_walking.repository.CommentsRepository;
import org.springframework.stereotype.Service;

@Data
@Service

public class CommentService {
    private final CommentsRepository commentRepository;

    public CommentService(CommentsRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentResponse createComment (CommentRequest request) {
        Comments comment = Comments.builder()
                .postId(request.getPostId())
                .userId(request.getUserId())
                .content(request.getContent())
                .parentCommentId(request.getParentCommentId())
                .build();

        Comments savedComment = commentRepository.save(comment);

        return new CommentResponse(
                savedComment.getCommentId(),
                savedComment.getParentCommentId(),
                savedComment.getPostId(),
                savedComment.getUserId(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getIsDeleted());
    }

    public void deleteComment(Long commentId) {
        Comments comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        comment.setIsDeleted(true);
        commentRepository.save(comment);
    }

    public CommentResponse modifyComment(Long commentId, CommentRequest request) {
        Comments comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        comment.setContent(request.getContent());
        Comments updatedComment = commentRepository.save(comment);

        return new CommentResponse(
                updatedComment.getCommentId(),
                updatedComment.getParentCommentId(),
                updatedComment.getPostId(),
                updatedComment.getUserId(),
                updatedComment.getContent(),
                updatedComment.getCreatedAt(),
                updatedComment.getIsDeleted());
    }

    public int getCommentCount(Long postId) {
        return (int) commentRepository.countByPostId(postId);
    }








}


