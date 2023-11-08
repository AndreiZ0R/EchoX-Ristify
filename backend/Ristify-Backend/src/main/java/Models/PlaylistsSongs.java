package Models;

import jakarta.persistence.*;

import java.util.Objects;


@Table(name = "playlistssongs")
public class PlaylistsSongs {
    @Column(nullable = false)
    private Long playlistId;
    @Column(nullable = false, unique = true)
    private Long songId;

    public PlaylistsSongs(){

    }

    public PlaylistsSongs(Long playlistId, Long songId) {
        this.playlistId = playlistId;
        this.songId = songId;
    }

    //Getters

    public Long getPlaylistId() {
        return playlistId;
    }

    public Long getSongId() {
        return songId;
    }

    //Setters

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    //equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistsSongs that = (PlaylistsSongs) o;
        return Objects.equals(playlistId, that.playlistId) && Objects.equals(songId, that.songId);
    }

    //hashCode

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, songId);
    }
}
