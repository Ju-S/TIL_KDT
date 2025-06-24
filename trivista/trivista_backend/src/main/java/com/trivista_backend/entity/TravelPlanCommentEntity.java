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
@Table(name = "travel_plan_comment")
public class TravelPlanCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "travel_plan_id")
    private TravelPlanEntity travelPlan;

    @Column
    private String comment;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
}
