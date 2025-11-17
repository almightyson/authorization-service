package com.pavittar.authorizationservice.common.specification.builder;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleSpecificationBuilder<T> {

    public Specification<T> buildFromFilter(Object filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Field field : filter.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(filter);
                    if (value != null) {
                        if (value instanceof String stringVal) {
                            // For string fields, use LIKE for partial match
                            predicates.add(cb.like(cb.lower(root.get(field.getName())), "%" + stringVal.toLowerCase() + "%"));
                        } else {
                            // For other types, use equality
                            predicates.add(cb.equal(root.get(field.getName()), value));
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error building specification from filter", e);
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

