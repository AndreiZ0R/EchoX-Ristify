package com.ristify.ristifybackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {
    @Id
    @JoinColumn(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(targetEntity = Playlist.class)
    private Long userId;

    @Column(unique = true, name = "username")
    private String username;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "firstName")
    private String firstName;

    @Column(nullable = false, name = "lastName")
    private String lastName;

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Column(nullable = false, name = "country")
    private String country;

    @Column(nullable = false, name = "createdAt")
    private Timestamp createdAt;

    @Column(nullable = false, name = "lastLogin")
    private Timestamp lastLogin;

    @Column(nullable = false, name = "birthDate")
    private Date birthDate;

    public User(
            final Long userId,
            final String username,
            final String password,
            final String firstName,
            final String lastName,
            final String email,
            final String country,
            final Timestamp createdAt,
            final Timestamp lastLogin,
            final Date birthDate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.birthDate = birthDate;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public void setCreatedAt(final Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastLogin(final Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(country, user.country) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(lastLogin, user.lastLogin) &&
                Objects.equals(birthDate, user.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, firstName, lastName, email, country, createdAt, lastLogin, birthDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                ", birthDate=" + birthDate +
                '}';
    }
}