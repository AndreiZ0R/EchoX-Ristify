package com.ristify.ristifybackend.models.playlist;

import com.ristify.ristifybackend.models.keys.PlaylistSongKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "playlistsongs")
@IdClass(PlaylistSongKey.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaylistSong {
    @Id
    @NonNull
    @ManyToOne(targetEntity = Playlist.class)
    @JoinColumn(name = "playlist_id", unique = true, nullable = false)
    private Playlist playlist;

    @Id
    @NonNull
    @ManyToOne(targetEntity = Song.class)
    @JoinColumn(name = "song_id", unique = true, nullable = false)
    private Song song;
}