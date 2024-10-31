package com.walking.project_walking.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostRequest {

    @NotNull(message = "게시글 작성자는 필수입니다.")
    private Long userId;

    @NotNull(message = "게시판 ID는 필수입니다.")
    private Long boardId;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    private String postImage;
}
