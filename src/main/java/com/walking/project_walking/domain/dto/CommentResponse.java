package com.walking.project_walking.domain.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder

public class CommentResponse {
    private final Long commentId;
    private final Long postId;
    private final Long userId;
    private final String content;
    private final LocalDateTime createdAt;
    private final Boolean isDeleted;
}
