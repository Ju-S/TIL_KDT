package com.trivista_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "transport")
public class TransportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(nullable = false)
    private String startAddress;

    @Setter
    @Column(nullable = false)
    private String goalAddress;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportType type;
    // 교통 타입(자가용, 기차, 버스 등)
}
