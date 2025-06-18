package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attraction")
public class AttractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String name;

    @Column
    String description;

    @Column
    String address;
}
