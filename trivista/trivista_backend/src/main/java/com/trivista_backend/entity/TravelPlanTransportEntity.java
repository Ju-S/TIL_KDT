package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "travel_plan_transport")
public class TravelPlanTransportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long travel_plan_id;

    @Column
    private long transport_id;
}
