package com.pavittar.authorizationservice.dto.user;

import com.pavittar.authorizationservice.dto.BaseDTO;

import java.util.UUID;

public class UserUpdateRequest extends BaseDTO {

    private String name;
    private String email;
    private String image;

}
