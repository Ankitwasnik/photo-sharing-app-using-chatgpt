package com.talentica.appusingchatgpt.repository;

import com.talentica.appusingchatgpt.model.Post;
import com.talentica.appusingchatgpt.model.Reaction;
import com.talentica.appusingchatgpt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
  Optional<Reaction> findByPostAndUser(Post post, User user);

  int countByPost(Post post);

}
