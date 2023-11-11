package com.ristify.ristifybackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Playlist {
    @Id
    @Column(name = "playlistId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistId;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "playlist")
    private Set<PlaylistSong> playlistSongs = new HashSet<>();
}