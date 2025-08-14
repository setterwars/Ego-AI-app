package com.egoai.duckdns.model;

import jakarta.persistence.*;


// Habit entity model. Store Id, name and UserId
@Entity
@Table(name = "habits")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long userId;

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return this.userId;
    }

    public String getName() {
        return name;
    }
}
