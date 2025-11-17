package com.pavittar.authorizationservice.repository;

import com.pavittar.authorizationservice.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for managing the Resource entity.
 * Provides basic CRUD methods and custom queries.
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID> {
}
