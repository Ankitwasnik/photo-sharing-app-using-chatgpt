package com.talentica.appusingchatgpt.controller;

import com.talentica.appusingchatgpt.dto.UserDTO;
import com.talentica.appusingchatgpt.dto.UserResponseDTO;
import com.talentica.appusingchatgpt.model.User;
import com.talentica.appusingchatgpt.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
    UserResponseDTO userResponse = userService.createUser(userDTO);
    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    UserResponseDTO userResponse = userService.getUserById(id);
    return new ResponseEntity<>(userResponse, HttpStatus.OK);
  }

  @GetMapping("/{userId}/ratings")
  public ResponseEntity<Integer> getUserRating(@PathVariable("userId") Long userId) {
    int rating = userService.calculateRating(userId);
    return new ResponseEntity<>(rating, HttpStatus.OK);
  }

}

