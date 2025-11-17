package com.pavittar.authorizationservice.repository;

import com.pavittar.authorizationservice.dto.role.RoleDTO;
import com.pavittar.authorizationservice.model.Permission;
import com.pavittar.authorizationservice.model.Role;
import com.pavittar.authorizationservice.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for managing the RolePermission entity.
 * Provides basic CRUD methods and custom queries.
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {
    List<Permission> findRolesByPermissionId(UUID permissionId);

    boolean existsByRoleIdAndPermissionId(UUID roleId, UUID permissionId);

    boolean deleteRolePermissionByRoleIdAndPermissionId(UUID roleId, UUID permissionId);

    List<Role> getAllRoleByPermissionId(UUID permissionId);
}
