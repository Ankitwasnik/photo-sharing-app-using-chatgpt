package com.talentica.appusingchatgpt.dto;

import com.talentica.appusingchatgpt.model.Reaction.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionResponseDTO {
  private Long id;
  private Long postId;
  private Long userId;
  private ReactionType reactionType;

}

