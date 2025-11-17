package com.pavittar.authorizationservice.service;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import com.pavittar.authorizationservice.dto.permission.PermissionDTO;
import com.pavittar.authorizationservice.dto.role.RoleDTO;
import com.pavittar.authorizationservice.exception.ConflictException;
import com.pavittar.authorizationservice.exception.NotFoundException;
import com.pavittar.authorizationservice.mapper.PermissionMapper;
import com.pavittar.authorizationservice.mapper.RoleMapper;
import com.pavittar.authorizationservice.model.Permission;
import com.pavittar.authorizationservice.model.Role;
import com.pavittar.authorizationservice.model.RolePermission;
import com.pavittar.authorizationservice.repository.PermissionRepository;
import com.pavittar.authorizationservice.repository.RolePermissionRepository;
import com.pavittar.authorizationservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    private final RolePermissionRepository rolePermissionRepository;

    public List<RoleDTO> getRoles() {
        List<Role> roles = roleRepository.findAll();

        return roles.stream().map(roleMapper::roleToRoleDTO).toList();
    }

    public RoleDTO getRoleById(UUID roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException(DomainCode.ROLE, ActionCode.FETCHED));

        return roleMapper.roleToRoleDTO(role);
    }

    public RoleDTO createRole(RoleDTO request) {
        Role role = roleRepository.save(roleMapper.roleDTOtoRole(request));

        return roleMapper.roleToRoleDTO(role);
    }

    public RoleDTO updateRole(UUID roleId, RoleDTO request) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException(DomainCode.ROLE, ActionCode.FETCHED));
        roleMapper.updateRoleFromReroleDTO(role, request);

        return roleMapper.roleToRoleDTO(role);
    }

    public void deleteRole(UUID roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new NotFoundException(DomainCode.ROLE, ActionCode.DELETED);
        }
        roleRepository.deleteById(roleId);
    }

    public List<PermissionDTO> getRolePermissions(UUID roleId) {
        List<Permission> permissions = rolePermissionRepository.findRolesByPermissionId(roleId);

        return permissions.stream().map(permissionMapper::permissionToPermissionDTO).toList();
    }

    public PermissionDTO addPermissionToRole(UUID roleId, UUID permissionId) {
        if(rolePermissionRepository.existsByRoleIdAndPermissionId(roleId, permissionId)){
            throw new ConflictException(DomainCode.ROLE, ActionCode.DUPLICATE);
        }

        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException(DomainCode.ROLE, ActionCode.FETCHED));
        Permission permission = permissionRepository.findById(roleId).orElseThrow(() -> new NotFoundException(DomainCode.PERMISSION, ActionCode.FETCHED));

        rolePermissionRepository.save(RolePermission.builder()
                        .roleId(role.getId())
                        .permissionId(permission.getId())
                        .build()
        );

         return permissionMapper.permissionToPermissionDTO(permission);
    }

    public void removePermissionFromRole(UUID roleId, UUID permissionId) {
        boolean deleted = rolePermissionRepository.deleteRolePermissionByRoleIdAndPermissionId(roleId, permissionId);
        if (!deleted) {
            throw new NotFoundException(DomainCode.ROLE, ActionCode.UNASSIGNED);
        }
    }

}
