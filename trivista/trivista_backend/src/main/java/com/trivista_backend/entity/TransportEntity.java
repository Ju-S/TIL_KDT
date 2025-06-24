package com.trivista_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transport")
public class TransportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String startAddress;

    @Column
    private String goalAddress;

    @Column
    private String type;
    // 교통 타입(자가용, 기차, 버스 등)
}
