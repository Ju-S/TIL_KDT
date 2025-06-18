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
@Table(name = "accomodation")
public class AccomodationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String address;

    @OneToMany(mappedBy = "accomodation")
    private List<TravelPlanAccomodationEntity> travel_plan_accomodation = new ArrayList<>();
}
