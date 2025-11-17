package com.pavittar.authorizationservice.mapper;

import com.pavittar.authorizationservice.dto.user.UserCreateRequest;
import com.pavittar.authorizationservice.dto.user.UserPatchRequest;
import com.pavittar.authorizationservice.dto.user.UserResponse;
import com.pavittar.authorizationservice.dto.user.UserUpdateRequest;
import com.pavittar.authorizationservice.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface UserMapper {

    UserResponse userToUserResponse(User entity);

    User userCreateRequestToUser(UserCreateRequest request);

    void updateUserFromUpdateUserRequest( @MappingTarget User entity, UserUpdateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchUserFromUserPatchRequest(@MappingTarget User entity, UserPatchRequest request);

}
