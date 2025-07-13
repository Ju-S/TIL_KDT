package com.trivista_backend.repository;

import com.trivista_backend.entity.UserLikedTravelPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikedTravelPlanRepository extends JpaRepository<UserLikedTravelPlanEntity, Long> {
}
