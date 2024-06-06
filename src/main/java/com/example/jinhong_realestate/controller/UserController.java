package com.example.jinhong_realestate.controller;

import com.example.jinhong_realestate.entity.User;
import com.example.jinhong_realestate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User userDetails) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPreferredLocation(userDetails.getPreferredLocation());
            user.setMinRent(userDetails.getMinRent());
            user.setMaxRent(userDetails.getMaxRent());
            user.setMinPrice(userDetails.getMinPrice());
            user.setMaxPrice(userDetails.getMaxPrice());
            return userRepository.save(user);
        }
        return null;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }
}