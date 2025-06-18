package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_travel_plan")
public class UserTravelPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    long travel_plan_id;

    @Column
    long user_id;
}
