package com.trivista_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "travel_plan_comment")
public class TravelPlanComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    long user_travel_plan_id;

    @Column
    String comment;

    @Column
    LocalDateTime created_at = LocalDateTime.now();
}
