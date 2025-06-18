package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_travel_plan")
public class UserTravelPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long travel_plan_id;

    @Column
    private long user_id;
}
