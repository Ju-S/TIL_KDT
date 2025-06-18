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
@Table(name = "travel_plan_accomodation")
public class TravelPlanAccommodationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_plan_id")
    private TravelPlanEntity travel_plan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accomodation_id")
    private AccommodationEntity accomodation;

    @Column
    private LocalDateTime check_in_datetime;

    @Column
    private LocalDateTime check_out_datetime;

    @Column
    private long approx_budget;
}
