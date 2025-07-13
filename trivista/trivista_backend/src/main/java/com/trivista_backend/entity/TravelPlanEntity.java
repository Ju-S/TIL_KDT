package com.trivista_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "travel_plan")
public class TravelPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID shareUuid = UUID.randomUUID();

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column
    private LocalDate fromDate;

    @Setter
    @Column
    private LocalDate toDate;

    @Setter
    @Column
    private long budgetLimit;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanType status;
    // 여행일정의 상태 표시
    // 1: 계획중, 2: 계획완료, 3: 계획종료

    @Setter
    @Column(nullable = false)
    private boolean visibility;

    @Setter
    @Column
    private String startAddress;

    @Setter
    @OneToMany(mappedBy = "travel_plan")
    private List<TravelPlanCommentEntity> travelPlanCommentList = new ArrayList<>();
}
