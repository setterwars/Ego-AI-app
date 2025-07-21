package com.best_habbit_tracher.best_habbit_tracker.controller;

import com.best_habbit_tracher.best_habbit_tracker.Security.jwtUtil;
import com.best_habbit_tracher.best_habbit_tracker.dto.UserDTO;
import com.best_habbit_tracher.best_habbit_tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private jwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }
}
