package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.PostDTO;

import com.talentica.appusingchatgpt.dto.PostResponseDTO;
import com.talentica.appusingchatgpt.dto.PostTrendDTO;
import com.talentica.appusingchatgpt.enums.TimeRange;
import com.talentica.appusingchatgpt.exception.ResourceNotFoundException;
import com.talentica.appusingchatgpt.model.Post;
import com.talentica.appusingchatgpt.model.User;
import com.talentica.appusingchatgpt.repository.PostRepository;
import com.talentica.appusingchatgpt.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public PostResponseDTO createPost(PostDTO postDTO, Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

    Post newPost = new Post();
    newPost.setUser(user);
    newPost.setCaption(postDTO.getCaption());
    newPost.setPhotoUrl(postDTO.getPhotoUrl());
    newPost.setCreatedAt(LocalDateTime.now());
    Post savedPost = postRepository.save(newPost);

    return mapPostToPostResponseDTO(savedPost);
  }

  @Override
  public Page<PostResponseDTO> getAllPosts(Pageable pageable) {
    Page<Post> posts = postRepository.findAll(pageable);
    List<PostResponseDTO> postResponses = posts.stream()
        .map(this::mapPostToPostResponseDTO)
        .collect(Collectors.toList());
    return new PageImpl<>(postResponses, pageable, posts.getTotalElements());
  }

  @Override
  public List<PostTrendDTO> getPostTrend(TimeRange timeRange, Instant startDateTime, Instant endDateTime) {
    String dateTrunc = timeRange.toString().toLowerCase();

    LocalDateTime localStartTime = LocalDateTime.ofInstant(startDateTime, ZoneId.of("UTC"));
    LocalDateTime localEndTime = LocalDateTime.ofInstant(endDateTime, ZoneId.of("UTC"));

    List<Object[]> resultList = postRepository.getPostTrend(dateTrunc, localStartTime, localEndTime);

    return resultList.stream()
        .map(record -> new PostTrendDTO(
            ((Date) record[0]).toInstant(),
            ((Number) record[1]).longValue()
        ))
        .collect(Collectors.toList());
  }

  private PostResponseDTO mapPostToPostResponseDTO(Post post) {
    PostResponseDTO postResponseDTO = new PostResponseDTO();
    postResponseDTO.setId(post.getId());
    postResponseDTO.setUserId(post.getUser().getId());
    postResponseDTO.setUsername(post.getUser().getUsername());
    postResponseDTO.setCaption(post.getCaption());
    postResponseDTO.setPhotoUrl(post.getPhotoUrl());
    postResponseDTO.setCreatedAt(post.getCreatedAt());
    return postResponseDTO;
  }
}

