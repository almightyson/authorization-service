package com.pavittar.authorizationservice.mapper;

import com.pavittar.authorizationservice.dto.permission.PermissionDTO;
import com.pavittar.authorizationservice.dto.role.RoleDTO;
import com.pavittar.authorizationservice.model.Permission;
import com.pavittar.authorizationservice.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface PermissionMapper {

    PermissionDTO permissionToPermissionDTO(Permission entity);

    Permission permissionDTOtoPermission(PermissionDTO dto);

    void updatePermissionFromPermissionDTO(@MappingTarget Permission entity, PermissionDTO dto);

}
