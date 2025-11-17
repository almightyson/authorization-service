package com.pavittar.authorizationservice.dto.user;

import com.pavittar.authorizationservice.dto.BaseDTO;

import java.util.UUID;

public class UserResponse extends BaseDTO {

    private UUID id;
    private String name;
    private String email;
    private String image;

}
