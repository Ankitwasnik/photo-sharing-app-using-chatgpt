package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.ReactionResponseDTO;
import com.talentica.appusingchatgpt.model.Reaction;
import com.talentica.appusingchatgpt.model.Reaction.ReactionType;

public interface ReactionService {
  ReactionResponseDTO createOrUpdateReaction(Long postId, Long userId, ReactionType reactionType);
}

