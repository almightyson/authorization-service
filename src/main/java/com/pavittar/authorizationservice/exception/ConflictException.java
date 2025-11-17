package com.pavittar.authorizationservice.exception;

import com.pavittar.authorizationservice.common.enums.ActionCode;
import com.pavittar.authorizationservice.common.enums.DomainCode;
import lombok.Getter;

@Getter
public class ConflictException extends AuthorizationServiceException {

    private final DomainCode domain;
    private final ActionCode action;
    private final String errorCode;
    private final String message;

    public ConflictException(DomainCode domain, ActionCode action) {
        this(domain, action, null);
    }

    public ConflictException(DomainCode domain, ActionCode action, String message) {
        super(message);
        this.domain = domain;
        this.action = action;
        this.errorCode = buildErrorCode(domain, action);
        this.message = message != null ? message : "Conflict occurred for " + domain.name().toLowerCase();
    }

    private static String buildErrorCode(DomainCode domain, ActionCode action) {
        return domain.name() + "_" + action.name() + "_CONFLICT";


    }
}




