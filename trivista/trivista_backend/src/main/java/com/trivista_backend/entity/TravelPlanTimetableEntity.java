package com.trivista_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "travel_plan_timetable")
public class TravelPlanTimetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlanEntity travelPlan;

    @Column(nullable = false)
    private long planId;

    @Column(nullable = false)
    private PlanType planType;

    @Column
    private long approxBudget;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;
}
