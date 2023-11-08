package Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "playlists")
public class Playlists {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    @Column(nullable = false, unique = true)
    private String name;

    public Playlists(){

    }

    public Playlists(Long id, Long userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    //Getters

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    //equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlists playlists = (Playlists) o;
        return Objects.equals(id, playlists.id) && Objects.equals(userId, playlists.userId) && Objects.equals(name, playlists.name);
    }

    //hashCode

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name);
    }
}
