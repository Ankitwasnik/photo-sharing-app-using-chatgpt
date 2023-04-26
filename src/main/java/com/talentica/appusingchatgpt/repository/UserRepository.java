package com.talentica.appusingchatgpt.repository;

import com.talentica.appusingchatgpt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // You can add custom query methods here, if necessary
}
