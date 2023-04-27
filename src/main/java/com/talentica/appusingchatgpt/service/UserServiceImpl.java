package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.UserDTO;
import com.talentica.appusingchatgpt.dto.UserResponseDTO;
import com.talentica.appusingchatgpt.exception.ResourceNotFoundException;
import com.talentica.appusingchatgpt.model.Post;
import com.talentica.appusingchatgpt.model.User;

import com.talentica.appusingchatgpt.repository.CommentRepository;
import com.talentica.appusingchatgpt.repository.PostRepository;
import com.talentica.appusingchatgpt.repository.ReactionRepository;
import com.talentica.appusingchatgpt.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private ReactionRepository reactionRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Override
  public UserResponseDTO createUser(UserDTO userDTO) {
    User newUser = new User();
    newUser.setUsername(userDTO.getUsername());
    newUser.setEmail(userDTO.getEmail());
    newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    newUser.setActive(true);
    newUser.setVerified(false);
    User savedUser = userRepository.save(newUser);

    return mapUserToUserResponseDTO(savedUser);
  }

  @Override
  public UserResponseDTO getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    return mapUserToUserResponseDTO(user);
  }

  @Override
  public int calculateRating(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    // Calculate points from submitted posts
    int postPoints = postRepository.countByUser(user) * 10;

    // Calculate points from reactions on submitted posts
    List<Post> userPosts = postRepository.findByUser(user);
    int reactionPoints = userPosts.stream()
        .mapToInt(post -> reactionRepository.countByPost(post))
        .sum();

    // Calculate points from comments on submitted posts
    int commentPoints = userPosts.stream()
        .mapToInt(post -> commentRepository.countByPostAndUserNot(post, user))
        .sum() * 2;

    return postPoints + reactionPoints + commentPoints;
  }


  private UserResponseDTO mapUserToUserResponseDTO(User user) {
    UserResponseDTO userResponseDTO = new UserResponseDTO();
    userResponseDTO.setId(user.getId());
    userResponseDTO.setUsername(user.getUsername());
    userResponseDTO.setEmail(user.getEmail());
    return userResponseDTO;
  }

}
