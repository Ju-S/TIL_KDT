package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "travel_plan_transport")
public class TravelPlanTransportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_plan_id")
    private TravelPlanEntity travel_plan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transport_id")
    private TransportEntity transport;
}
