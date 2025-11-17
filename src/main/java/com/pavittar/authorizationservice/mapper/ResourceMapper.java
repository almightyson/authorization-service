package com.pavittar.authorizationservice.mapper;

import com.pavittar.authorizationservice.dto.resource.ResourceDTO;
import com.pavittar.authorizationservice.dto.user.UserUpdateRequest;
import com.pavittar.authorizationservice.model.Resource;
import com.pavittar.authorizationservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ResourceMapper {

    ResourceDTO resourceToResourceDTO(Resource entity);

    Resource resourceDTOtoResource(ResourceDTO dto);

    void updateUserFromResourceDTO(@MappingTarget Resource entity, ResourceDTO dto);

}
