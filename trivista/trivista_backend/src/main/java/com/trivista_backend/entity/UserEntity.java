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
    private long userId;

    @Column
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private ProviderType provider = ProviderType.KAKAO;

    @OneToMany(mappedBy = "user")
    private List<UserTravelPlanEntity> userTravelPlanList = new ArrayList<>();
}
