package com.walking.project_walking.domain.dto;

import com.walking.project_walking.domain.Comments;
import lombok.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AddCommentRequest {

    private Long postId;
    private Long userId;

    @NotBlank(message = "댓글을 작성해주세요.")
    private String content;

}



