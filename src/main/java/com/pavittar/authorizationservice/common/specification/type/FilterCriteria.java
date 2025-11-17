package com.pavittar.authorizationservice.common.specification.type;

public record FilterCriteria(String field, Operator operator, Object value) {}