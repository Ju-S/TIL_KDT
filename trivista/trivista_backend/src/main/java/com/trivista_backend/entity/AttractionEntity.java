package com.trivista_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attraction")
public class AttractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String address;
}
