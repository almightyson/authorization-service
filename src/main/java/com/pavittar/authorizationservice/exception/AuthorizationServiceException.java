package com.pavittar.authorizationservice.exception;

public abstract class AuthorizationServiceException extends RuntimeException{
    protected AuthorizationServiceException(String message) {
        super(message);
    }
}