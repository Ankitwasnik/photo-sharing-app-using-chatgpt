package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.PostDTO;
import com.talentica.appusingchatgpt.dto.PostResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
  PostResponseDTO createPost(PostDTO postDTO, Long userId);
  Page<PostResponseDTO> getAllPosts(Pageable pageable);
}
