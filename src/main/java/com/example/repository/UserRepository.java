package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    Optional<User> findByUsername(String username);

}
