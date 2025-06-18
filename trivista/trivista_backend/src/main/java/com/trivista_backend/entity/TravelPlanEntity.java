package com.trivista_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "travel_plan")
public class TravelPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    long UUID;

    @Column
    LocalDate from_date = LocalDate.now();

    @Column
    LocalDate to_date = LocalDate.now();

    @Column
    long budget_limit;

    @Column
    int status;

    @Column
    String start_address;
}
