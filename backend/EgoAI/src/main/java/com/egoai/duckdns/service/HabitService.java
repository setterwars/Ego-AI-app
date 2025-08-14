package com.egoai.duckdns.service;

import com.egoai.duckdns.model.Habit;
import com.egoai.duckdns.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service for interaction with habit repository
@Service
public class HabitService {
    @Autowired
    private HabitRepository habitRepository;

    public Habit CreateHabit(long userId, String name) {
        Habit habit = new Habit();

        habit.setName(name);
        habit.setUserId(userId);

        return habitRepository.save(habit);
    }

    public List<Habit> getAllHabit() {
        return  habitRepository.findAll();
    }
}
