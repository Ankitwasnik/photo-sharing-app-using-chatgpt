package com.talentica.appusingchatgpt.controller;

import com.talentica.appusingchatgpt.dto.CommentResponseDTO;
import com.talentica.appusingchatgpt.dto.CustomUserDetails;
import com.talentica.appusingchatgpt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
  @Autowired
  private CommentService commentService;

  @PostMapping
  public ResponseEntity<CommentResponseDTO> createComment(
      @PathVariable("postId") Long postId,
      @RequestParam("content") String content) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();

    CommentResponseDTO comment = commentService.createComment(postId, userId, content);
    return new ResponseEntity<>(comment, HttpStatus.CREATED);
  }
}

