package com.trivista_backend.repository;

import com.trivista_backend.entity.UserTravelPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTravelPlanRepository extends JpaRepository<UserTravelPlanEntity, Long> {
}
