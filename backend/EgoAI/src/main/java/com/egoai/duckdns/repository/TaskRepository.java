package com.egoai.duckdns.repository;

import com.egoai.duckdns.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository for interaction with Task models
public interface TaskRepository extends JpaRepository<Task, Long>{
    Optional<Task> findById(long id);
}
