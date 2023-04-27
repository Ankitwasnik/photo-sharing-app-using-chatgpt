package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.ReactionResponseDTO;
import com.talentica.appusingchatgpt.exception.ResourceNotFoundException;
import com.talentica.appusingchatgpt.model.Post;
import com.talentica.appusingchatgpt.model.Reaction;
import com.talentica.appusingchatgpt.model.Reaction.ReactionType;

import com.talentica.appusingchatgpt.model.User;
import com.talentica.appusingchatgpt.repository.PostRepository;
import com.talentica.appusingchatgpt.repository.ReactionRepository;
import com.talentica.appusingchatgpt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService {
  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ReactionRepository reactionRepository;

  @Override
  public ReactionResponseDTO createOrUpdateReaction(Long postId, Long userId, ReactionType reactionType) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    Reaction reaction = reactionRepository.findByPostAndUser(post, user)
        .orElseGet(() -> {
          Reaction newReaction = new Reaction();
          newReaction.setPost(post);
          newReaction.setUser(user);
          return newReaction;
        });

    reaction.setReactionType(reactionType);

    Reaction savedReaction = reactionRepository.save(reaction);

    return new ReactionResponseDTO(
        savedReaction.getId(),
        savedReaction.getPost().getId(),
        savedReaction.getUser().getId(),
        savedReaction.getReactionType()
    );
  }
}

