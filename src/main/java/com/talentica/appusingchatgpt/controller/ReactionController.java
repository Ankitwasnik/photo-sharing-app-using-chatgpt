package com.talentica.appusingchatgpt.controller;

import com.talentica.appusingchatgpt.dto.CustomUserDetails;
import com.talentica.appusingchatgpt.dto.ReactionResponseDTO;
import com.talentica.appusingchatgpt.model.Reaction;
import com.talentica.appusingchatgpt.model.Reaction.ReactionType;
import com.talentica.appusingchatgpt.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/{postId}/reactions")
public class ReactionController {
  @Autowired
  private ReactionService reactionService;

  @PostMapping
  public ResponseEntity<ReactionResponseDTO> createOrUpdateReaction(
      @PathVariable("postId") Long postId,
      @RequestParam("reactionType") ReactionType reactionType) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();

    ReactionResponseDTO reaction = reactionService.createOrUpdateReaction(postId, userId, reactionType);
    return new ResponseEntity<>(reaction, HttpStatus.CREATED);
  }
}

