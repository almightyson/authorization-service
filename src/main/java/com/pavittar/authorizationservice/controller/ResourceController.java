package com.pavittar.authorizationservice.controller;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import com.pavittar.authorizationservice.dto.api.ApiResponse;
import com.pavittar.authorizationservice.dto.api.ApiResponseFactory;
import com.pavittar.authorizationservice.dto.permission.PermissionDTO;
import com.pavittar.authorizationservice.dto.resource.ResourceDTO;
import com.pavittar.authorizationservice.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<ResourceDTO>>> getResources(){
        List<ResourceDTO> resources = resourceService.getResources();

        return ApiResponseFactory.ok(DomainCode.RESOURCE, ActionCode.FETCHED, resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceDTO>> getResource(@PathVariable UUID id){
        ResourceDTO resource = resourceService.getResourceById(id);

        return ApiResponseFactory.ok(DomainCode.RESOURCE, ActionCode.FETCHED, resource);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<ResourceDTO>> createResource(@RequestBody @Valid ResourceDTO request){
        ResourceDTO resource = resourceService.createResource(request);

        return ApiResponseFactory.created(DomainCode.RESOURCE, ActionCode.CREATED, resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceDTO>> updateResource(@PathVariable UUID id, @RequestBody @Valid ResourceDTO request){
        ResourceDTO resource = resourceService.updateResource(id, request);

        return ApiResponseFactory.ok(DomainCode.RESOURCE, ActionCode.UPDATED, resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteResource(@PathVariable UUID id){
        resourceService.deleteResource(id);

        return ApiResponseFactory.ok(DomainCode.RESOURCE, ActionCode.DELETED, null);
    }

    @GetMapping("/{id}/permissions")
    public ResponseEntity<ApiResponse<List<PermissionDTO>>> getResourcePermissions(@PathVariable UUID id){
        List<PermissionDTO> permissions = resourceService.getResourcePermissions(id);

        return ApiResponseFactory.ok(DomainCode.PERMISSION, ActionCode.FETCHED, permissions);
    }

}
