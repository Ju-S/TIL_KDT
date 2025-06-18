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
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String user_id;

    @Column
    private String password;

    @Column
    private String address;

    @OneToMany(mappedBy = "user")
    private List<UserTravelPlanEntity> user_travel_plan_list = new ArrayList<>();
}
