package com.pavittar.authorizationservice.repository;

import com.pavittar.authorizationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for managing the User entity.
 * Provides basic CRUD methods and custom queries.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    /**
     * Finds a user by email
     * @param email the user's email
     * @return Optional<User> if found, empty otherwise
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user with the given email exists
     * @param email the user's email
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);

}
