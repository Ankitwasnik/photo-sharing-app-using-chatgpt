package com.talentica.appusingchatgpt.dto;

import java.time.Instant;

public class PostTrendDTO {
  private Instant period;
  private Long postCount;

  public PostTrendDTO(Instant period, Long postCount) {
    this.period = period;
    this.postCount = postCount;
  }

  public Instant getPeriod() {
    return period;
  }

  public void setPeriod(Instant period) {
    this.period = period;
  }

  public Long getPostCount() {
    return postCount;
  }

  public void setPostCount(Long postCount) {
    this.postCount = postCount;
  }
}
