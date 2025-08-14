package com.egoai.duckdns.repository;

import com.egoai.duckdns.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository for interaction with Habit models
public interface HabitRepository extends JpaRepository<Habit, Long> {
    Optional<Habit> findByUserId(long userId);
}
