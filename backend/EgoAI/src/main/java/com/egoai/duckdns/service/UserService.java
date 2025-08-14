package com.egoai.duckdns.service;

import com.egoai.duckdns.model.User;
import com.egoai.duckdns.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Service for interaction with userRepository
@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private PasswordEncoder PasswordEncoder;

    public User register(String email, String password) {
        if (UserRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already used");
        }

        User user = new User();
        user.setEmail(email);
        user.setRole("USER");
        user.setPasswordHash(PasswordEncoder.encode(password));

        return UserRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = UserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!PasswordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid Password");
        }

        return user;
    }

    public User findByEmail(String email) {
        return UserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
