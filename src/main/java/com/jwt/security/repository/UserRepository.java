package com.jwt.security.repository;

import com.jwt.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}
