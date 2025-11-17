package com.pavittar.authorizationservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents a permission
 */
@Entity
@Table(name = "permissions")
@Comment("Represents a permission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission {

    /**
     * Unique identifier for each permission
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    @Column(name = "id", nullable = false)
    @Comment("Unique identifier for each permission")
    private UUID id;

    /**
     * Permission name
     */
    @NotNull
    @Column(name = "name", nullable = false)
    @Comment("Permission name")
    private String name;

    /**
     * Permission description
     */
    @NotNull
    @Column(name = "description", nullable = false)
    @Comment("Permission description")
    private String description;

    /**
     * Permission is active or not
     */
    @NotNull
    @Column(name = "active", nullable = false)
    @Comment("Permission is active or not")
    private String active;

    /**
     * Resource that the permission is for
     */
    @NotNull
    @Column(name = "resource_id", nullable = false)
    @Comment(" Resource that the permission is for")
    private UUID resourceId;

    /**
     * Action that can be done on the resource
     */
    @NotNull
    @Column(name = "action", nullable = false)
    @Comment("Action that can be done on the resource")
    private String action;



    /**
     * Timestamp of when the permission was created
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Comment("Timestamp of when the permission was created")
    private Instant createdAt;

    /**
     * Timestamp of the last update to the permission information
     */
    @NotNull
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @Comment("Timestamp of the last update to the permission information")
    private Instant updatedAt;

}
