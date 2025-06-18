package com.trivista_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "travel_plan")
public class TravelPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long UUID;

    @Column
    private LocalDate from_date = LocalDate.now();

    @Column
    private LocalDate to_date = LocalDate.now();

    @Column
    private long budget_limit;

    @Column
    private int status;

    @Column
    private String start_address;
}
