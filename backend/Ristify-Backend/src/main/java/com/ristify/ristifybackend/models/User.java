package com.ristify.ristifybackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @Column(name = "userId", unique = true, nullable = false)
    private Integer userId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @Column(name = "lastLogin", nullable = false)
    private Timestamp lastLogin;

    @Column(name = "birthDate", nullable = false)
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private Set<Playlist> playlists = new HashSet<>();
}