package com.pavittar.authorizationservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents a resource
 */
@Entity
@Table(name = "resources")
@Comment("Represents a resource")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resource {

    /**
     * Unique identifier for each resource
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    @Column(name = "id", nullable = false)
    @Comment("Unique identifier for each resource")
    private UUID id;

    /**
     * Resource name
     */
    @Null
    @Column(name = "name", nullable = true)
    @Comment("Resource name")
    private String name;

    /**
     * Resource description
     */
    @NotNull
    @Column(name = "description", nullable = false)
    @Comment("Resource description")
    private String description;


    /**
     * Timestamp of when the resource was created
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Comment("Timestamp of when the resource was created")
    private Instant createdAt;

    /**
     * Timestamp of the last update to the resource information
     */
    @NotNull
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @Comment("Timestamp of the last update to the resource information")
    private Instant updatedAt;

}
