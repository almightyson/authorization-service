package com.pavittar.authorizationservice.common.enums;

public enum ActionCode {

        // CRUD
        FETCHED,
        CREATED,
        UPDATED,
        UPDATED_PARTIALLY,
        DELETED,

        // Relation / link operations
        ASSIGNED,          // generic assignment
        UNASSIGNED,        // generic removal of relation
        RELATION_ADDED,    // optional alternative
        RELATION_REMOVED,  // optional alternative

        // Security / Auth
        SUCCESS,
        INVALID_CREDENTIALS,
        TOKEN_EXPIRED,
        FORBIDDEN,
        LOCKED,
        MFA_REQUIRED,

        // Validation / business rules
        VALIDATION_ERROR,
        RULE_VIOLATION,
        NOT_FOUND,
        UNSUPPORTED,

        // Bulk / async operations
        BULK_PARTIAL_SUCCESS,
        QUEUED,
        IN_PROGRESS,
        COMPLETED,
        FAILED,
        TIMEOUT,
        CANCELLED,

        // System / infra
        INTERNAL_ERROR,
        SERVICE_UNAVAILABLE,
        DEPRECATED,
        DUPLICATE

}
