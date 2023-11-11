package com.ristify.ristifybackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "songs")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Song {
    @Id
    @ManyToOne(targetEntity = Playlist.class)
    @JoinColumn(name = "songId", unique = true, nullable = false)
    private Long songId;

    @Column(name = "songName", nullable = false)
    private String songName;

    @Column(name = "artistName", nullable = false)
    private String artistName;

    @Column(name = "albumName", nullable = false)
    private String albumName;

    @Column(name = "url", unique = true, nullable = false)
    private String url;

    @OneToMany(mappedBy = "song")
    private Set<PlaylistSong> playlistSongs = new HashSet<>();
}