package com.trivista_backend.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "travel_plan_attraction")
public class TravelPlanAttractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    long travel_plan_id;

    @Column
    long attraction_id;

    @Column
    LocalTime start_time;

    @Column
    LocalTime end_time;

    @Column
    long approx_budget;
}
