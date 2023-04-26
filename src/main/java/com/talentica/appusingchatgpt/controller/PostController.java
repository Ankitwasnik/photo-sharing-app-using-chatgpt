package com.talentica.appusingchatgpt.controller;

import com.talentica.appusingchatgpt.dto.CustomUserDetails;
import com.talentica.appusingchatgpt.dto.PostDTO;
import com.talentica.appusingchatgpt.dto.PostResponseDTO;
import com.talentica.appusingchatgpt.dto.PostTrendDTO;
import com.talentica.appusingchatgpt.enums.TimeRange;
import com.talentica.appusingchatgpt.service.PostService;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  @Autowired
  private PostService postService;

  @PostMapping
  public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostDTO postDTO, Authentication authentication) {
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    Long userId = userDetails.getId();
    PostResponseDTO postResponse = postService.createPost(postDTO, userId);
    return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Page<PostResponseDTO>> getAllPosts(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<PostResponseDTO> postResponses = postService.getAllPosts(pageable);
    return new ResponseEntity<>(postResponses, HttpStatus.OK);
  }

  @GetMapping("/trend")
  public ResponseEntity<List<PostTrendDTO>> getPostTrend(@RequestParam("timeRange") TimeRange timeRange,
      @RequestParam("startDateTime") Instant startDateTime,
      @RequestParam("endDateTime") Instant endDateTime) {
    List<PostTrendDTO> postTrend = postService.getPostTrend(timeRange, startDateTime, endDateTime);
    return new ResponseEntity<>(postTrend, HttpStatus.OK);
  }
}

