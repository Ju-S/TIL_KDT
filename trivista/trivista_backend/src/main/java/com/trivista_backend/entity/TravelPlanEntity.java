package com.trivista_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "travel_plan")
public class TravelPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID share_uuid = UUID.randomUUID();

    @Column
    private LocalDate from_date = LocalDate.now();

    @Column
    private LocalDate to_date = LocalDate.now();

    @Column
    private long budget_limit;

    @Column
    private int status;

    @Column
    private String start_address;

    @OneToMany(mappedBy = "travel_plan")
    private List<TravelPlanTransportEntity> travel_plan_transport_list = new ArrayList<>();

    @OneToMany(mappedBy = "travel_plan")
    private List<TravelPlanAccommodationEntity> travel_plan_accommodation_list = new ArrayList<>();

    @OneToMany(mappedBy = "travel_plan")
    private List<TravelPlanCommentEntity> travel_plan_comment_list = new ArrayList<>();

    @OneToMany(mappedBy = "travel_plan")
    private List<TravelPlanAttractionEntity> travel_plan_attraction_list = new ArrayList<>();
}
