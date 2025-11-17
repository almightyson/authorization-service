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
 * Represents a role
 */
@Entity
@Table(name = "roles")
@Comment("Represents a role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    /**
     * Unique identifier for each role
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    @Column(name = "id", nullable = false)
    @Comment("Unique identifier for each role")
    private UUID id;

    /**
     * Role name
     */
    @NotNull
    @Column(name = "name", nullable = false)
    @Comment("Role name")
    private String name;

    /**
     * Role description
     */
    @Null
    @Column(name = "description", nullable = true)
    @Comment("Role description")
    private String description;

    /**
     * Parent Role reference key
     */
    @NotNull
    @Column(name = "parent_id", nullable = false)
    @Comment("Parent Role reference key")
    private String parentId;

    /**
     * Whether role is active
     */
    @NotNull
    @Column(name = "active", nullable = false)
    @Comment("Whether role is active")
    private boolean active;

    /**
     * Timestamp of when the role was created
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Comment("Timestamp of when the role was created")
    private Instant createdAt;

    /**
     * Timestamp of the last update to the role information
     */
    @NotNull
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @Comment("Timestamp of the last update to the role information")
    private Instant updatedAt;

}
