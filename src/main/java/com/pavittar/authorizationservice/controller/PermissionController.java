package com.pavittar.authorizationservice.controller;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import com.pavittar.authorizationservice.dto.api.ApiResponse;
import com.pavittar.authorizationservice.dto.api.ApiResponseFactory;
import com.pavittar.authorizationservice.dto.permission.PermissionDTO;
import com.pavittar.authorizationservice.dto.resource.ResourceDTO;
import com.pavittar.authorizationservice.dto.role.RoleDTO;
import com.pavittar.authorizationservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<PermissionDTO>>> getPermissions() {
        List<PermissionDTO> permissions = permissionService.getPermissions();

        return ApiResponseFactory.ok(DomainCode.PERMISSION, ActionCode.FETCHED, permissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionDTO>> getPermission(@PathVariable UUID id) {
        PermissionDTO permission = permissionService.getPermission(id);

        return ApiResponseFactory.ok(DomainCode.PERMISSION, ActionCode.FETCHED, permission);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<PermissionDTO>> createPermission(@RequestBody PermissionDTO request) {
        PermissionDTO permission = permissionService.createPermission(request);

        return ApiResponseFactory.created(DomainCode.PERMISSION, ActionCode.CREATED, permission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionDTO>> updatePermission(@PathVariable UUID id, @RequestBody PermissionDTO request) {
        PermissionDTO permission = permissionService.updatePermission(id, request);

        return ApiResponseFactory.ok(DomainCode.PERMISSION, ActionCode.UPDATED, permission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePermission(@PathVariable UUID id) {
        permissionService.deletePermission(id);

        return ApiResponseFactory.ok(DomainCode.PERMISSION, ActionCode.DELETED, null);
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getPermissionRoles(@PathVariable UUID id) {
        List<RoleDTO> roles = permissionService.getPermissionRoles(id);

        return ApiResponseFactory.ok(DomainCode.ROLE, ActionCode.FETCHED, roles);
    }

    @GetMapping("/{id}/resources")
    public ResponseEntity<ApiResponse<ResourceDTO>> getPermissionResource(@PathVariable UUID id) {
        ResourceDTO resource = permissionService.getPermissionResource(id);

        return ApiResponseFactory.ok(DomainCode.RESOURCE, ActionCode.FETCHED, resource);
    }

}
