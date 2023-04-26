package com.talentica.appusingchatgpt.service;

import com.talentica.appusingchatgpt.dto.UserDTO;
import com.talentica.appusingchatgpt.dto.UserResponseDTO;

public interface UserService {
  UserResponseDTO createUser(UserDTO userDTO);
  UserResponseDTO getUserById(Long id);
}
