package com.talentica.appusingchatgpt.repository;

import com.talentica.appusingchatgpt.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  // You can add custom query methods here, if necessary
}
