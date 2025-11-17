package com.pavittar.authorizationservice.mapper;

import com.pavittar.authorizationservice.dto.role.RoleDTO;
import com.pavittar.authorizationservice.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface RoleMapper {

    RoleDTO roleToRoleDTO(Role entity);

    Role roleDTOtoRole(RoleDTO dto);

    void updateRoleFromReroleDTO(@MappingTarget Role entity, RoleDTO dto);

}
