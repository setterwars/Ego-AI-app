package com.egoai.duckdns.controller;

import com.egoai.duckdns.repository.HabitRepository;
import com.egoai.duckdns.security.JwtUtil;
import com.egoai.duckdns.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Habit controllers. All endpoint related to habit
@RestController
@RequestMapping("/habits")
@Tag(name = "Habits", description = "Habit management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class HabitController {
    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
}
