package com.trivista_backend.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "transport")
public class TransportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String start_address;

    @Column
    String goal_address;

    @Column
    LocalTime travel_time;
}
