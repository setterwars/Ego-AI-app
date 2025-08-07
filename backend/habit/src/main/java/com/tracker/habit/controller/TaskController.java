package com.tracker.habit.controller;

import com.tracker.habit.model.Task;
import com.tracker.habit.model.User;
import com.tracker.habit.service.TaskService;
import com.tracker.habit.service.UserService;
import com.tracker.habit.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Task management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("")
    @Operation(summary = "Create a new task", description = "Create a new task for the authenticated user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task created successfully"),
        @ApiResponse(responseCode = "401", description = "Invalid or missing JWT token")
    })
    public ResponseEntity<?> createTask(
            @RequestBody Map<String, Object> payload,
            @Parameter(hidden = true) @RequestHeader("Authorization") String authHeader) {
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
    @Operation(summary = "Mark task as done", description = "Mark a specific task as completed")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task marked as done successfully"),
        @ApiResponse(responseCode = "401", description = "Invalid or missing JWT token"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<?> setTaskDone(
            @PathVariable @Parameter(description = "Task ID") long id,
            @Parameter(hidden = true) @RequestHeader("Authorization") String authHeader) {
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
    @Operation(summary = "Mark task as not done", description = "Mark a specific task as not completed")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task marked as not done successfully"),
        @ApiResponse(responseCode = "401", description = "Invalid or missing JWT token"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<?> setTaskUnDone(
            @PathVariable @Parameter(description = "Task ID") long id,
            @Parameter(hidden = true) @RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        Task task = taskService.SetTaskUnDone(id);
        return ResponseEntity.ok(task);
    }
}
