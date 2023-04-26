package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.UserDTO;
import com.talentica.appusingchatgpt.dto.UserResponseDTO;
import com.talentica.appusingchatgpt.exception.ResourceNotFoundException;
import com.talentica.appusingchatgpt.model.User;

import com.talentica.appusingchatgpt.repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

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


  private UserResponseDTO mapUserToUserResponseDTO(User user) {
    UserResponseDTO userResponseDTO = new UserResponseDTO();
    userResponseDTO.setId(user.getId());
    userResponseDTO.setUsername(user.getUsername());
    userResponseDTO.setEmail(user.getEmail());
    return userResponseDTO;
  }

}
