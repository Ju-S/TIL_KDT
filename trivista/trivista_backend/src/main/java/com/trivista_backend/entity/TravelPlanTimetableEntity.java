package com.trivista_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "travel_plan_timetable")
public class TravelPlanTimetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlanEntity travelPlan;

    @Setter
    @Column(nullable = false)
    private long planId;
    // 일정 종류에 따른 테이블의 행의 id.
    // e.g, 식당 중, 선택된 식당의 id.

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanType planType;
    // 일정 종류(식당, 숙소, 관광지 중)

    @Setter
    @Column
    private long approxBudget;

    @Setter
    @Column
    private LocalDateTime startTime;

    @Setter
    @Column
    private LocalDateTime endTime;
}
