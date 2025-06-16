package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transport")
public class TransportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
}
