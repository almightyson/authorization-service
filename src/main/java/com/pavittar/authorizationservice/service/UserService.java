package com.pavittar.authorizationservice.service;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import com.pavittar.authorizationservice.common.specification.builder.SimpleSpecificationBuilder;
import com.pavittar.authorizationservice.common.specification.builder.SpecificationBuilder;
import com.pavittar.authorizationservice.common.specification.type.FilterCriteria;
import com.pavittar.authorizationservice.dto.role.RoleDTO;
import com.pavittar.authorizationservice.dto.user.UserCreateRequest;
import com.pavittar.authorizationservice.dto.user.UserPatchRequest;
import com.pavittar.authorizationservice.dto.user.UserResponse;
import com.pavittar.authorizationservice.dto.user.UserUpdateRequest;
import com.pavittar.authorizationservice.exception.ConflictException;
import com.pavittar.authorizationservice.exception.NotFoundException;
import com.pavittar.authorizationservice.filter.UserFilter;
import com.pavittar.authorizationservice.mapper.RoleMapper;
import com.pavittar.authorizationservice.mapper.UserMapper;
import com.pavittar.authorizationservice.model.Role;
import com.pavittar.authorizationservice.model.User;
import com.pavittar.authorizationservice.model.UserRole;
import com.pavittar.authorizationservice.repository.RoleRepository;
import com.pavittar.authorizationservice.repository.UserRepository;
import com.pavittar.authorizationservice.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    private final UserRoleRepository userRoleRepository;
    //private final RoleMapper roleMapper;

    private final SpecificationBuilder<User> specificationBuilder;
    private final SimpleSpecificationBuilder<User> simpleSpecificationBuilder;

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserResponse)
                .toList();
    }

    public Page<UserResponse> searchUsers(UserFilter filter, Pageable pageable) {
        Specification<User> spec = simpleSpecificationBuilder.buildFromFilter(filter);
        Page<User> users = userRepository.findAll(spec, pageable);
        return users.map(userMapper::userToUserResponse);
    }

    public Page<UserResponse> searchUsersAdvanced(List<FilterCriteria> filters, Pageable pageable) {
        Specification<User> spec = specificationBuilder.buildFromFilters(filters);
        Page<User> users = userRepository.findAll(spec, pageable);
        return users.map(userMapper::userToUserResponse);
    }

    public UserResponse getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(DomainCode.USER, ActionCode.FETCHED));
        return userMapper.userToUserResponse(user);
    }

    public UserResponse createUser(UserCreateRequest request) {
        User user = userRepository.save(userMapper.userCreateRequestToUser(request));
        return userMapper.userToUserResponse(user);
    }

    public UserResponse updateUser(UUID userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(DomainCode.USER, ActionCode.FETCHED));
        userMapper.updateUserFromUpdateUserRequest(user, request);
        return userMapper.userToUserResponse(userRepository.save(user));
    }

    public UserResponse patchUser(UUID userId, UserPatchRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(DomainCode.USER, ActionCode.FETCHED));
        userMapper.patchUserFromUserPatchRequest(user, request);
        return userMapper.userToUserResponse(userRepository.save(user));
    }

    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(DomainCode.USER, ActionCode.DELETED);
        }
        userRepository.deleteById(userId);
    }

    public List<RoleDTO> getUserRoles(UUID userId) {
        List<Role> roles = userRoleRepository.findRolesByUserId(userId);

        return roles.stream().map(roleMapper::roleToRoleDTO).toList();
    }

    public RoleDTO assignRoleToUser(UUID userId, UUID roleId) {
        if (userRoleRepository.existsByUserIdAndRoleId(userId, roleId)) {
            throw new ConflictException(DomainCode.USER, ActionCode.DUPLICATE);
        }

        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(DomainCode.USER, ActionCode.FETCHED));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException(DomainCode.ROLE, ActionCode.FETCHED));


        userRoleRepository.save(UserRole.builder()
                .userId(userId)
                .roleId(roleId)
                .build());

        return roleMapper.roleToRoleDTO(role);
    }

    public void unassignRoleFromUser(UUID userId, UUID roleId) {
        boolean deleted = userRoleRepository.deleteUserRoleByUserIdAndRoleId(userId, roleId);
        if (!deleted) {
            throw new NotFoundException(DomainCode.ROLE, ActionCode.UNASSIGNED);
        }
    }

}
