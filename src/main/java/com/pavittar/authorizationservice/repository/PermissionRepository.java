package com.pavittar.authorizationservice.repository;

import com.pavittar.authorizationservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for managing the Permission entity.
 * Provides basic CRUD methods and custom queries.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    List<Permission> getAllByResourceId(UUID resourceId);
}
