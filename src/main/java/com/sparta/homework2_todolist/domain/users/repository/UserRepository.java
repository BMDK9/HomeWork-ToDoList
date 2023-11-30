package com.sparta.homework2_todolist.domain.users.repository;

import com.sparta.homework2_todolist.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
