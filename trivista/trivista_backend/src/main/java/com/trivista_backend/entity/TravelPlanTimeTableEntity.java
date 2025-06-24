package com.trivista_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "travel_plan_timetable")
public class TravelPlanTimeTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String userId;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserTravelPlanEntity> userTravelPlanList = new ArrayList<>();
}
