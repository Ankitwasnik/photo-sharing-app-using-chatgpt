package com.talentica.appusingchatgpt.repository;

import com.talentica.appusingchatgpt.model.Comment;
import com.talentica.appusingchatgpt.model.Post;
import com.talentica.appusingchatgpt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  // You can add custom query methods here, if necessary

  int countByPostAndUserNot(Post post, User user);

}
