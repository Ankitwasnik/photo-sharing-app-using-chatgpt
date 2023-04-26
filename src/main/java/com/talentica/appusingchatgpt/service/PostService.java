package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.PostDTO;
import com.talentica.appusingchatgpt.dto.PostResponseDTO;
import com.talentica.appusingchatgpt.dto.PostTrendDTO;
import com.talentica.appusingchatgpt.enums.TimeRange;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
  PostResponseDTO createPost(PostDTO postDTO, Long userId);
  Page<PostResponseDTO> getAllPosts(Pageable pageable);

  List<PostTrendDTO> getPostTrend(TimeRange timeRange, Instant startDateTime, Instant endDateTime);
}
