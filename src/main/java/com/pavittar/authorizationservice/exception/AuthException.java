package com.pavittar.authorizationservice.exception;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import lombok.Getter;

@Getter
public class AuthException extends AuthorizationServiceException {

    private final DomainCode domain;
    private final ActionCode action;
    private final String errorCode;
    private final String message;

    public AuthException(DomainCode domain, ActionCode action) {
        this(domain, action, null);
    }

    public AuthException(DomainCode domain, ActionCode action, String message) {
        super(message);
        this.domain = domain;
        this.action = action;
        this.errorCode = buildErrorCode(domain, action);
        this.message = message != null ? message : "Authentication/Authorization error for " + domain.name().toLowerCase();
    }

    private static String buildErrorCode(DomainCode domain, ActionCode action) {
        return domain.name() + "_" + action.name() + "_AUTH_ERROR";
    }
}