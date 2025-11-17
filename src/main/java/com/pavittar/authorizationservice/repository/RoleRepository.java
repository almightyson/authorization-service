package com.pavittar.authorizationservice.repository;

import com.pavittar.authorizationservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for managing the Role entity.
 * Provides basic CRUD methods and custom queries.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
