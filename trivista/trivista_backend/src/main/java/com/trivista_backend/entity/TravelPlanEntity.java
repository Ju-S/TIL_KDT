package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "travel_plan")
public class TravelPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
}
