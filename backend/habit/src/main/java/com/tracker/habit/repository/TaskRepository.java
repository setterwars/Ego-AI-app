package com.tracker.habit.repository;

import com.tracker.habit.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>{
    Optional<Task> findById(long id);
}
