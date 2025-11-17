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
 * Represents a user
 */
@Entity
@Table(name = "users")
@Comment("Represents a user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    /**
     * Unique identifier for each user
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    @Column(name = "id", nullable = false)
    @Comment("Unique identifier for each user")
    private UUID id;

    /**
     * User's chosen display name
     */
    @NotNull
    @Column(name = "name", nullable = false)
    @Comment("User's chosen display name")
    private String name;

    /**
     * User's email address for communication and login
     */
    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    @Comment("User's email address for communication and login")
    private String email;

    /**
     * Whether the user's email is verified
     */
    @NotNull
    @Column(name = "email_verified", nullable = false)
    @Comment("Whether the user's email is verified")
    private boolean emailVerified = false;

    /**
     * Whether the user's is active
     */
    @NotNull
    @Column(name = "active", nullable = false)
    @Comment("Whether the user's is active")
    private boolean active = false;

    /**
     * User's image url
     */
    @Column(name = "image", nullable = true)
    @Comment("User's image url")
    private String image;

    /**
     * Timestamp of when the user account was created
     */
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Comment("Timestamp of when the user account was created")
    private Instant createdAt;

    /**
     * Timestamp of the last update to the user's information
     */
    @NotNull
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @Comment("Timestamp of the last update to the user's information")
    private Instant updatedAt;

}
