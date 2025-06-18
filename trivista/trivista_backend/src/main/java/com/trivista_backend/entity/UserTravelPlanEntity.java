package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_travel_plan")
public class UserTravelPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_plan_id")
    private TravelPlanEntity travel_plan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
