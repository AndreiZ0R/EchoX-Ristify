package com.ristify.ristifybackend.models;

import com.ristify.ristifybackend.models.composite.keys.PlaylistSongKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "playlistsongs")
@IdClass(PlaylistSongKey.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PlaylistSong {
    @Id
    @JoinColumn(name = "playlist", unique = true, nullable = false, referencedColumnName = "playlistId")
    @ManyToOne(targetEntity = Playlist.class)
    private Integer playlist;

    @Id
    @JoinColumn(name = "song", unique = true, nullable = false, referencedColumnName = "songId")
    @ManyToOne(targetEntity = Song.class)
    private Integer song;
}