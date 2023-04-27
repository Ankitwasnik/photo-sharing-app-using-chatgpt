package com.talentica.appusingchatgpt.repository;

import com.talentica.appusingchatgpt.model.Post;
import com.talentica.appusingchatgpt.model.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  // You can add custom query methods here, if necessary

  @Query(value = "SELECT DATE_TRUNC(:dateTrunc, p.created_at) AS period, COUNT(p.id) AS post_count " +
      "FROM posts p " +
      "WHERE p.created_at BETWEEN :startDateTime AND :endDateTime " +
      "GROUP BY period " +
      "ORDER BY period", nativeQuery = true)
  List<Object[]> getPostTrend(String dateTrunc, LocalDateTime startDateTime, LocalDateTime endDateTime);


  int countByUser(User user);

  List<Post> findByUser(User user);

}
