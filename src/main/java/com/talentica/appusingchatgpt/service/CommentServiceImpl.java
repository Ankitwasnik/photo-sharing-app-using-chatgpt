package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.CommentResponseDTO;
import com.talentica.appusingchatgpt.exception.ResourceNotFoundException;
import com.talentica.appusingchatgpt.model.Comment;
import com.talentica.appusingchatgpt.model.Post;
import com.talentica.appusingchatgpt.model.User;
import com.talentica.appusingchatgpt.repository.CommentRepository;
import com.talentica.appusingchatgpt.repository.PostRepository;
import com.talentica.appusingchatgpt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Override
  public CommentResponseDTO createComment(Long postId, Long userId, String content) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    Comment comment = new Comment();
    comment.setPost(post);
    comment.setUser(user);
    comment.setContent(content);
    Comment savedComment = commentRepository.save(comment);

    return new CommentResponseDTO(
        savedComment.getId(),
        savedComment.getPost().getId(),
        savedComment.getUser().getId(),
        savedComment.getContent()
    );
  }
}

