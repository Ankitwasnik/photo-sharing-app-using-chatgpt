package com.talentica.appusingchatgpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
  private Long id;
  private Long postId;
  private Long userId;
  private String content;

}

