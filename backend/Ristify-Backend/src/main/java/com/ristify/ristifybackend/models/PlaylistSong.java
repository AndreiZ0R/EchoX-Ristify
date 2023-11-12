package com.ristify.ristifybackend.models;

import com.ristify.ristifybackend.models.keys.PlaylistSongKey;
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
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "playlistsongs")
@IdClass(PlaylistSongKey.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PlaylistSong {
    @Id
    @ManyToOne(targetEntity = Playlist.class)
    @JoinColumn(name = "playlist_id", unique = true, nullable = false)
    private Playlist playlist;

    @Id
    @ManyToOne(targetEntity = Song.class)
    @JoinColumn(name = "song_id", unique = true, nullable = false)
    private Song song;
}