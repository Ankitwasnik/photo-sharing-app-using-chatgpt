package com.talentica.appusingchatgpt.repository;

import com.talentica.appusingchatgpt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  // You can add custom query methods here, if necessary
}
