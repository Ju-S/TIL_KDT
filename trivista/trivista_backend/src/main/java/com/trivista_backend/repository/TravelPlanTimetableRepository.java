package com.trivista_backend.repository;

import com.trivista_backend.entity.TravelPlanTimetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelPlanTimetableRepository extends JpaRepository<TravelPlanTimetableEntity, Long> {
}
