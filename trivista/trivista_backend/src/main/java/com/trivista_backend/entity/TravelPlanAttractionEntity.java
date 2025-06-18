package com.trivista_backend.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "travel_plan_attraction")
public class TravelPlanAttractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long travel_plan_id;

    @Column
    private long attraction_id;

    @Column
    private LocalTime start_time;

    @Column
    private LocalTime end_time;

    @Column
    private long approx_budget;
}
