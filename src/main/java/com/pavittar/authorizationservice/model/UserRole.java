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
 * Represents the relation between user and role
 */
@Entity
@Table(name = "user_role")
@Comment("Represents the relation between user and role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {

    /**
     * Unique identifier for each relation
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    @Column(name = "id", nullable = false)
    @Comment("Unique identifier for each relation")
    private UUID id;

    /**
     * User reference id
     */
    @NotNull
    @Column(name = "user_id", nullable = false)
    @Comment("User reference id")
    private UUID userId;

    /**
     * Role reference id
     */
    @NotNull
    @Column(name = "role_id", nullable = false)
    @Comment("Role reference id")
    private UUID roleId;

    /**
     * Timestamp of when the relation was created
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Comment("Timestamp of when the relation was created")
    private Instant createdAt;

    /**
     * Timestamp of the last update to the relation information
     */
    @NotNull
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @Comment("Timestamp of the last update to the relation information")
    private Instant updatedAt;

}
