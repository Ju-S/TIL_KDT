package com.trivista_backend.repository;

import com.trivista_backend.entity.TravelPlanAttractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPlanAttractionRepository extends JpaRepository<TravelPlanAttractionEntity, Long> {
}
