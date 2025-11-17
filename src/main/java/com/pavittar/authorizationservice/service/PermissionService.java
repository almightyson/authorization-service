package com.pavittar.authorizationservice.service;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;

import com.pavittar.authorizationservice.dto.permission.PermissionDTO;
import com.pavittar.authorizationservice.dto.resource.ResourceDTO;
import com.pavittar.authorizationservice.dto.role.RoleDTO;
import com.pavittar.authorizationservice.exception.NotFoundException;
import com.pavittar.authorizationservice.mapper.PermissionMapper;
import com.pavittar.authorizationservice.mapper.ResourceMapper;
import com.pavittar.authorizationservice.mapper.RoleMapper;
import com.pavittar.authorizationservice.model.Permission;
import com.pavittar.authorizationservice.model.Resource;
import com.pavittar.authorizationservice.model.Role;
import com.pavittar.authorizationservice.repository.PermissionRepository;
import com.pavittar.authorizationservice.repository.ResourceRepository;
import com.pavittar.authorizationservice.repository.RolePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleMapper roleMapper;

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    public List<PermissionDTO> getPermissions() {
        List<Permission> permissions = permissionRepository.findAll();

        return permissions.stream().map(permissionMapper::permissionToPermissionDTO).toList();
    }

    public PermissionDTO getPermission(UUID id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(DomainCode.PERMISSION, ActionCode.FETCHED));

        return permissionMapper.permissionToPermissionDTO(permission);
    }

    public PermissionDTO createPermission(PermissionDTO request) {
        Permission permission = permissionRepository.save(permissionMapper.permissionDTOtoPermission(request));

        return permissionMapper.permissionToPermissionDTO(permission) ;
    }

    public PermissionDTO updatePermission(UUID id, PermissionDTO request) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(DomainCode.PERMISSION, ActionCode.FETCHED));
        permissionMapper.updatePermissionFromPermissionDTO(permission, request);

        return permissionMapper.permissionToPermissionDTO(permission) ;
    }

    public void deletePermission( UUID id) {
        if(!permissionRepository.existsById(id)) {
            throw new NotFoundException(DomainCode.PERMISSION, ActionCode.DELETED);
        }

        permissionRepository.deleteById(id); ;
    }

    public List<RoleDTO> getPermissionRoles( UUID id) {
        List<Role> roles = rolePermissionRepository.getAllRoleByPermissionId(id);

        return roles.stream().map(roleMapper::roleToRoleDTO).toList() ;
    }

    public ResourceDTO getPermissionResource(UUID id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(DomainCode.PERMISSION,ActionCode.FETCHED));

        Resource resource = resourceRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(DomainCode.RESOURCE,ActionCode.FETCHED));

        return resourceMapper.resourceToResourceDTO(resource);
    }
}
