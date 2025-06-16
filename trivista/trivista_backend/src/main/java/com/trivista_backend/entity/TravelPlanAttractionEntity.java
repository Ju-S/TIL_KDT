package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "travel_plan_attraction")
public class TravelPlanAttractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
}
