package com.egoai.duckdns.service;

import com.egoai.duckdns.model.Task;
import com.egoai.duckdns.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

// Service for interaction with TaskRepository
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task CreateTask(long userId, String title, String description, Date deadline) {
        Task task = new Task();

        task.setUserId(userId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task SetTaskDone(long taskId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setDone();
        return taskRepository.save(task);
    }

    public Task SetTaskUnDone(long taskId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setUnDone();
        return taskRepository.save(task);
    }
}
