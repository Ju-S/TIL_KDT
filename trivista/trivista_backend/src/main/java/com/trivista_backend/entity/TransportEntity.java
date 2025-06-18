package com.trivista_backend.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "transport")
public class TransportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String start_address;

    @Column
    private String goal_address;

    @Column
    private LocalTime travel_time;

    @Column
    private String type;
    // 교통 타입(자가용, 기차, 버스 등)
}
