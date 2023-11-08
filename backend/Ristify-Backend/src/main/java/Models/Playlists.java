package Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "playlists")
public class Playlists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column(name = "userid")
    @OneToOne(targetEntity = Users.class)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String name;

    public Playlists(){

    }

    public Playlists(final Long id, final Long userId, final String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlists playlists = (Playlists) o;
        return Objects.equals(id, playlists.id) && Objects.equals(userId, playlists.userId) && Objects.equals(name, playlists.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name);
    }
}
