package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String hello(){
        return "lkalkslkalksa";
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody User user) {
        if (user == null || user.getUsername() == null) {
            return ResponseEntity.badRequest().body("Invalid user data");
        }
        try {
            userService.saveUser(user);
            return ResponseEntity.ok("User saved successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to save user: " + e.getMessage());
        }
    }
}
