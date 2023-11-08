package Models;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(targetEntity = Playlists.class)
    private Long id;
    @Column(unique = true, name = "username")
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, name = "firstname")
    private String firstName;
    @Column(nullable = false, name = "lastname")
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false, name = "createdat")
    private Timestamp createdAt;
    @Column(nullable = false, name = "lastlogin")
    private Timestamp lastLogin;
    @Column(nullable = false, name = "birthdate")
    private Date birthDate;

    public Users(){

    }

    public Users(final Long id,final String username,final String password,final String firstName,final String lastName,final String email,final String country,final Timestamp createdAt,final Timestamp lastLogin,final Date birthDate) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setId(final Long id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(firstName, users.firstName) && Objects.equals(lastName, users.lastName) && Objects.equals(email, users.email) && Objects.equals(country, users.country) && Objects.equals(createdAt, users.createdAt) && Objects.equals(lastLogin, users.lastLogin) && Objects.equals(birthDate, users.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email, country, createdAt, lastLogin, birthDate);
    }
}
