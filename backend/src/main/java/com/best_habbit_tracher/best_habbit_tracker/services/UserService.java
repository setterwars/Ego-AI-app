package com.best_habbit_tracher.best_habbit_tracker.services;

import com.best_habbit_tracher.best_habbit_tracker.dto.UserDTO;
import com.best_habbit_tracher.best_habbit_tracker.Entity.User;
import com.best_habbit_tracher.best_habbit_tracker.repositories.UserRepository;
import com.best_habbit_tracher.best_habbit_tracker.Security.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private jwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPasswordHash()));

        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(userDTO.getPasswordHash(), user.getPasswordHash())) {
            return jwtUtil.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
