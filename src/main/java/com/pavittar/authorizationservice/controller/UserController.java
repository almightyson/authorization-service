package com.pavittar.authorizationservice.controller;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import com.pavittar.authorizationservice.common.specification.type.FilterCriteria;
import com.pavittar.authorizationservice.dto.api.ApiResponse;
import com.pavittar.authorizationservice.dto.api.ApiResponseFactory;
import com.pavittar.authorizationservice.dto.role.RoleDTO;
import com.pavittar.authorizationservice.dto.user.UserCreateRequest;
import com.pavittar.authorizationservice.dto.user.UserPatchRequest;
import com.pavittar.authorizationservice.dto.user.UserResponse;
import com.pavittar.authorizationservice.dto.user.UserUpdateRequest;
import com.pavittar.authorizationservice.filter.UserFilter;
import com.pavittar.authorizationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {
        List<UserResponse> users = userService.getUsers();

        return ApiResponseFactory.ok(DomainCode.USER, ActionCode.FETCHED, users);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<UserResponse>>> searchUsers(@ModelAttribute UserFilter filter, Pageable pageable) {
        Page<UserResponse> users = userService.searchUsers(filter, pageable);

        return ApiResponseFactory.ok(DomainCode.USER, ActionCode.FETCHED, users);
    }

    @PostMapping("/search/advanced")
    public ResponseEntity<ApiResponse<Page<UserResponse>>> searchUsersAdvanced(@RequestBody List<FilterCriteria> filters, Pageable pageable) {
        Page<UserResponse> users = userService.searchUsersAdvanced(filters, pageable);

        return ApiResponseFactory.ok(DomainCode.USER, ActionCode.FETCHED, users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable UUID id) {
        UserResponse user = userService.getUserById(id);

        return ApiResponseFactory.ok(DomainCode.USER, ActionCode.FETCHED, user);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Validated UserCreateRequest request) {
        UserResponse user = userService.createUser(request);

        return ApiResponseFactory.created(DomainCode.USER, ActionCode.CREATED, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable UUID id, @RequestBody @Validated UserUpdateRequest request) {
        UserResponse user = userService.updateUser(id, request);

        return ApiResponseFactory.ok(DomainCode.USER, ActionCode.UPDATED, user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable UUID id, @RequestBody @Validated UserPatchRequest request) {
        UserResponse user = userService.patchUser(id, request);

        return ApiResponseFactory.ok(DomainCode.USER, ActionCode.UPDATED_PARTIALLY, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        return ApiResponseFactory.ok(DomainCode.USER, ActionCode.DELETED, null);
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getUserRoles(@PathVariable UUID id) {
        List<RoleDTO> roles = userService.getUserRoles(id);

        return ApiResponseFactory.ok(DomainCode.ROLE, ActionCode.FETCHED, roles);
    }

    @PostMapping("/{id}/roles/{roleId}")
    public ResponseEntity<ApiResponse<RoleDTO>> addRoleToUser(@PathVariable UUID id, @PathVariable UUID roleId) {
        RoleDTO role = userService.assignRoleToUser(id, roleId);

        return ApiResponseFactory.created(DomainCode.ROLE, ActionCode.ASSIGNED, role);
    }

    @DeleteMapping("/{id}/roles/{roleId}")
    public ResponseEntity<ApiResponse<Void>> removeRoleFromUser(@PathVariable UUID id, @PathVariable UUID roleId) {
        userService.unassignRoleFromUser(id, roleId);

        return ApiResponseFactory.ok(DomainCode.ROLE, ActionCode.UNASSIGNED, null);
    }

}
