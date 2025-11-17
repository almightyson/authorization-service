package com.pavittar.authorizationservice.repository;

import com.pavittar.authorizationservice.model.Role;
import com.pavittar.authorizationservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for managing the UserRole entity.
 * Provides basic CRUD methods and custom queries.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    @Query("""
       select r
       from Role r
       join UserRole ur on ur.roleId = r.id
       where ur.userId = :userId
       """)
    List<Role> findRolesByUserId(UUID userId);

    boolean existsByUserIdAndRoleId(UUID userId, UUID roleId);

    boolean deleteUserRoleByUserIdAndRoleId(UUID userId, UUID roleId);
}
