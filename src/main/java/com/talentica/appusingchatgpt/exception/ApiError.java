package com.talentica.appusingchatgpt.exception;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ApiError {
  private int statusCode;
  private String message;
  private LocalDateTime timestamp;

  public ApiError(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }

}

