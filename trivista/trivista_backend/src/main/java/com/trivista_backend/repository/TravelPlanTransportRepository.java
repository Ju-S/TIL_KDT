package com.trivista_backend.repository;

import com.trivista_backend.entity.TravelPlanTransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPlanTransportRepository extends JpaRepository<TravelPlanTransportEntity, Long> {
}
