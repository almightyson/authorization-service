package com.pavittar.authorizationservice.controller;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import com.pavittar.authorizationservice.dto.api.ApiResponse;
import com.pavittar.authorizationservice.dto.api.ApiResponseFactory;
import com.pavittar.authorizationservice.dto.permission.PermissionDTO;
import com.pavittar.authorizationservice.dto.role.RoleDTO;
import com.pavittar.authorizationservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getRoles() {
        List<RoleDTO> roles = roleService.getRoles();

        return ApiResponseFactory.ok(DomainCode.ROLE, ActionCode.FETCHED, roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> getRole(@PathVariable UUID id) {
        RoleDTO role = roleService.getRoleById(id);

        return ApiResponseFactory.ok(DomainCode.ROLE, ActionCode.FETCHED, role);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<RoleDTO>> createRole(@RequestBody @Validated RoleDTO request) {
        RoleDTO role = roleService.createRole(request);

        return ApiResponseFactory.created(DomainCode.ROLE, ActionCode.CREATED, role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> updateRole(@PathVariable UUID id, RoleDTO request) {
        RoleDTO role = roleService.updateRole(id, request);

        return ApiResponseFactory.ok(DomainCode.ROLE, ActionCode.UPDATED, role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);

        return ApiResponseFactory.ok(DomainCode.ROLE, ActionCode.DELETED, null);
    }

    @GetMapping("/{id}/permissions")
    public ResponseEntity<ApiResponse<List<PermissionDTO>>> getRolePermissions(@PathVariable UUID id) {
        List<PermissionDTO> permissions = roleService.getRolePermissions(id);

        return ApiResponseFactory.ok(DomainCode.PERMISSION, ActionCode.FETCHED, permissions);
    }

    @PostMapping("/{id}/permissions/{permissionId}")
    public ResponseEntity<ApiResponse<PermissionDTO>> addPermissionToRole(@PathVariable UUID id, @PathVariable UUID permissionId) {
        PermissionDTO permission = roleService.addPermissionToRole(id, permissionId);

        return ApiResponseFactory.ok(DomainCode.PERMISSION, ActionCode.ASSIGNED, permission);
    }

    @DeleteMapping("/{id}/permissions/{permissionId}")
    public ResponseEntity<ApiResponse<Void>> removePermissionFromRole(@PathVariable UUID id, @PathVariable UUID permissionId) {
        roleService.removePermissionFromRole(id, permissionId);

        return ApiResponseFactory.ok(DomainCode.PERMISSION, ActionCode.UNASSIGNED, null);
    }

}