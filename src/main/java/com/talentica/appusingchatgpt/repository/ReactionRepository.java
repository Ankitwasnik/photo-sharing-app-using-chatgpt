package com.talentica.appusingchatgpt.repository;

import com.talentica.appusingchatgpt.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
  // You can add custom query methods here, if necessary
}
