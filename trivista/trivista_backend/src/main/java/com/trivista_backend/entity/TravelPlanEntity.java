package com.trivista_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "travel_plan")
public class TravelPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID shareUuid = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDate fromDate;

    @Column
    private LocalDate toDate;

    @Column
    private long budgetLimit;

    @Column
    private int status;
    // 여행일정의 상태 표시
    // TODO: enum, converter 작성
    // 1: 계획중, 2: 계획완료, 3: 계획종료

    @Column(nullable = false)
    private boolean visibility;

    @Column
    private String startAddress;;

    @OneToMany(mappedBy = "travel_plan")
    private List<TravelPlanCommentEntity> travelPlanCommentList = new ArrayList<>();
}
