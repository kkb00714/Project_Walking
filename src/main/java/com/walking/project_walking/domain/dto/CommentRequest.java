package com.walking.project_walking.domain.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentRequest {

    private Long postId;
    private Long userId;
    private Long parentCommentId;

    @NotBlank(message = "댓글을 작성해주세요.")
    private String content;

}



