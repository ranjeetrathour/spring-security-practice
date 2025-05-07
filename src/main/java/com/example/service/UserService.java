package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        if (user == null) {
            log.warn("User is null. Save operation aborted.");
            return;
        }
        if (user.getUsername() == null ) {
            log.warn("User has missing required fields (username/email). Save aborted. User: {}", user);
            return;
        }
        try {
            userRepository.save(user);
            log.info("User saved successfully: {}", user.getUsername());
        } catch (Exception e) {
            log.error("Failed to save user: {}", user, e);
        }
    }
}
