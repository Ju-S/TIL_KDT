package com.trivista_backend.repository;

import com.trivista_backend.entity.TravelPlanAccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPlanAccommodationRepository extends JpaRepository<TravelPlanAccommodationEntity, Long> {
}
