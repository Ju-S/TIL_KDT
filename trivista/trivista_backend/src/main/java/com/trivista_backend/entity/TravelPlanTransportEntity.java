package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "travel_plan_transport")
public class TravelPlanTransportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    long travel_plan_id;

    @Column
    long transport_id;
}
