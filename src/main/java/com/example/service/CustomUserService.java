package com.example.service;

import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Add this


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user details for username: {}", username);

        return userRepository.findByUsername(username)
                .map(user -> {
                    String role = "admin";
                    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                    Set<GrantedAuthority> authorities = Collections.singleton(authority);
                    log.info("User details loaded successfully for username: {}", username);
                    return new User(
                            user.getUsername(),
                            user.getPassword(),
                            authorities
                    );
                })
                .orElseThrow(() -> {
                    log.error("User not found with username: {}", username);
                    return new UsernameNotFoundException("User not found with username: " + username);
                });
    }
}