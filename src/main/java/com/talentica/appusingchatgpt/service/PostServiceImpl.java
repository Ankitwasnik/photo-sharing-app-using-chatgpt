package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.PostDTO;

import com.talentica.appusingchatgpt.dto.PostResponseDTO;
import com.talentica.appusingchatgpt.exception.ResourceNotFoundException;
import com.talentica.appusingchatgpt.model.Post;
import com.talentica.appusingchatgpt.model.User;
import com.talentica.appusingchatgpt.repository.PostRepository;
import com.talentica.appusingchatgpt.repository.UserRepository;
import java.time.LocalDateTime;
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

