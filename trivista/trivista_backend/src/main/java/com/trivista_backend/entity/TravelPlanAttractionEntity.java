package com.trivista_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "travel_plan_attraction")
public class TravelPlanAttractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_plan_id")
    private TravelPlanEntity travel_plan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attraction_id")
    private AttractionEntity attraction;

    @Column
    private LocalTime start_time;

    @Column
    private LocalTime end_time;

    @Column
    private long approx_budget;
}
