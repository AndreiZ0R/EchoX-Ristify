package com.ristify.ristifybackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "playlists")
@NoArgsConstructor
@Getter
public class Playlist {
    @Id
    @Column(name = "playlistId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    @OneToMany(targetEntity = User.class)
    @JoinColumn(name = "user")
    private Set<Long> users;

    @Column(nullable = false, unique = true)
    private String name;

    public Playlist(final Long id, final Set<Long> users, final String name) {
        this.playlistId = id;
        this.users = users;
        this.name = name;
    }

    public void setPlaylistId(final Long id) {
        this.playlistId = id;
    }

    public void setUsers(final Set<Long> userId) {
        this.users = userId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(playlistId, playlist.playlistId) &&
                Objects.equals(users, playlist.users) &&
                Objects.equals(name, playlist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, users, name);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + playlistId +
                ", users=" + users +
                ", name='" + name + '\'' +
                '}';
    }
}