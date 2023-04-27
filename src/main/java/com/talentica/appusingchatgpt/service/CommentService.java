package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.CommentResponseDTO;

public interface CommentService {
  CommentResponseDTO createComment(Long postId, Long userId, String content);
}

