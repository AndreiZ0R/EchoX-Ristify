package com.ristify.ristifybackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Table(name = "playlistsongs")
@NoArgsConstructor
@Getter
public class PlaylistSong {
    @Column(nullable = false, name = "playlist")
    @OneToOne(targetEntity = Playlist.class)
    private Long playlist;

    @Column(nullable = false, unique = true, name = "song")
    @OneToOne(targetEntity = Song.class)
    private Long song;

    public PlaylistSong(final Long playlist, final Long song) {
        this.playlist = playlist;
        this.song = song;
    }

    public void setPlaylist(final Long playlist) {
        this.playlist = playlist;
    }

    public void setSong(final Long song) {
        this.song = song;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistSong that = (PlaylistSong) o;
        return Objects.equals(playlist, that.playlist) && Objects.equals(song, that.song);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlist, song);
    }

    @Override
    public String toString() {
        return "PlaylistSong{" +
                "playlist=" + playlist +
                ", song=" + song +
                '}';
    }
}