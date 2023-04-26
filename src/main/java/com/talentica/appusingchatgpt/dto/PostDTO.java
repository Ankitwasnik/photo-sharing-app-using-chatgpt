package com.talentica.appusingchatgpt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDTO {

  @NotBlank
  @Size(max = 255)
  private String caption;

  @NotBlank
  private String photoUrl;

}
