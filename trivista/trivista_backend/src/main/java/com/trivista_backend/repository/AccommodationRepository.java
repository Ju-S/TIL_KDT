package com.trivista_backend.repository;

import com.trivista_backend.entity.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<AccommodationEntity, Long> {
}
