package com.pavittar.authorizationservice.service;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import com.pavittar.authorizationservice.dto.permission.PermissionDTO;
import com.pavittar.authorizationservice.dto.resource.ResourceDTO;
import com.pavittar.authorizationservice.exception.NotFoundException;
import com.pavittar.authorizationservice.mapper.PermissionMapper;
import com.pavittar.authorizationservice.mapper.ResourceMapper;
import com.pavittar.authorizationservice.model.Permission;
import com.pavittar.authorizationservice.model.Resource;
import com.pavittar.authorizationservice.repository.PermissionRepository;
import com.pavittar.authorizationservice.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public List<ResourceDTO> getResources() {
        List<Resource> resources = resourceRepository.findAll();

        return resources.stream().map(resourceMapper::resourceToResourceDTO).toList();
    }

    public ResourceDTO getResourceById(UUID resourceId) {
        Resource resources = resourceRepository.findById(resourceId).orElseThrow(()-> new NotFoundException(DomainCode.RESOURCE, ActionCode.FETCHED));

        return resourceMapper.resourceToResourceDTO(resources);
    }

    public ResourceDTO createResource(ResourceDTO resourceDTO) {
        Resource resources = resourceRepository.save(resourceMapper.resourceDTOtoResource(resourceDTO));

        return resourceMapper.resourceToResourceDTO(resources);
    }

    public ResourceDTO updateResource(UUID resourceId, ResourceDTO resourceDTO) {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(()-> new NotFoundException(DomainCode.RESOURCE, ActionCode.FETCHED));
        resourceMapper.updateUserFromResourceDTO(resource, resourceDTO);

        return resourceMapper.resourceToResourceDTO(resource);
    }

    public void deleteResource(UUID resourceId) {
        if (!resourceRepository.existsById(resourceId)) {
            throw new NotFoundException(DomainCode.RESOURCE, ActionCode.DELETED);
        }
        resourceRepository.deleteById(resourceId);
    }

    public List<PermissionDTO> getResourcePermissions(UUID resourceId) {
        List<Permission> permissions = permissionRepository.getAllByResourceId(resourceId);

        return permissions.stream().map(permissionMapper::permissionToPermissionDTO).toList();
    }
}
