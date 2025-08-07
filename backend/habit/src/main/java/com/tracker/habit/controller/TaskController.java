package com.tracker.habit.controller;

import com.tracker.habit.model.Task;
import com.tracker.habit.model.User;
import com.tracker.habit.service.TaskService;
import com.tracker.habit.service.UserService;
import com.tracker.habit.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("")
    public ResponseEntity<?> createTask(@RequestBody Map<String, Object> payload, @RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        String email = jwtUtil.extractEmail(token);
        User user = userService.findByEmail(email);
        long userId = user.getId();

        String title = (String) payload.get("title");
        String description = (String) payload.get("description");
        Date deadline = payload.get("deadline") != null ? new Date((Long) payload.get("deadline")) : null;

        Task task = taskService.CreateTask(userId, title, description, deadline);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/{id}/done")
    public ResponseEntity<?> setTaskDone(@PathVariable long id, @RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        Task task = taskService.SetTaskDone(id);
        return ResponseEntity.ok(task);
    }

    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("Missing or invalid Authorization header");
    }

    @PostMapping("/{id}/undone")
    public ResponseEntity<?> setTaskUnDone(@PathVariable long id, @RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        Task task = taskService.SetTaskUnDone(id);
        return ResponseEntity.ok(task);
    }
}
