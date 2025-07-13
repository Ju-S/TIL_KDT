package com.trivista_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private long userId;

    @Setter
    @Column
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private ProviderType provider = ProviderType.KAKAO;
    // 소셜 로그인 제공자 타입(카카오, 네이버, 구글 등)

    @Setter
    @OneToMany(mappedBy = "user")
    private List<UserTravelPlanEntity> userTravelPlanList = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "user")
    private List<UserLikedTravelPlanEntity> userLikedTravelPlanList = new ArrayList<>();
}
