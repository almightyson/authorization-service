package com.pavittar.authorizationservice.common.specification.builder;

import com.pavittar.authorizationservice.common.specification.type.FilterCriteria;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpecificationBuilder<T> {

    public Specification<T> buildFromFilters(List<FilterCriteria> filters) {
        return (Root<T> root, CriteriaQuery<?> query,
                jakarta.persistence.criteria.CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filters == null || filters.isEmpty()) {
                return cb.conjunction();
            }

            for (FilterCriteria f : filters) {
                if (f.value() == null) continue;

                Path<?> path = getPath(root, f.field());

                switch (f.operator()) {
                    case EQUALS -> predicates.add(cb.equal(path, f.value()));

                    case CONTAINS -> {
                        if (path.getJavaType() == String.class) {
                            predicates.add(cb.like(cb.lower(path.as(String.class)),
                                    "%" + f.value().toString().toLowerCase() + "%"));
                        }
                    }

                    case STARTS_WITH -> {
                        if (path.getJavaType() == String.class) {
                            predicates.add(cb.like(cb.lower(path.as(String.class)),
                                    f.value().toString().toLowerCase() + "%"));
                        }
                    }

                    case IN -> predicates.add(path.in((List<?>) f.value()));

                    case GREATER_THAN -> predicates.add(
                            cb.greaterThan(path.as(Comparable.class), (Comparable) f.value()));

                    case LESS_THAN -> predicates.add(
                            cb.lessThan(path.as(Comparable.class), (Comparable) f.value()));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Path<?> getPath(Path<?> root, String field) {
        if (field.contains(".")) {
            String[] parts = field.split("\\.");
            Path<?> path = root;
            for (String part : parts) {
                path = path.get(part);
            }
            return path;
        }
        return root.get(field);
    }
}
