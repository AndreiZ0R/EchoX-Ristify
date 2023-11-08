package Models;

import jakarta.persistence.*;

import java.util.Objects;


@Table(name = "playlistssongs")
public class PlaylistsSongs {
    @Column(nullable = false, name = "playlistid")
    @OneToOne(targetEntity = Playlists.class)
    private Long playlistId;
    @Column(nullable = false, unique = true, name = "songid")
    @OneToOne(targetEntity = Songs.class)
    private Long songId;

    public PlaylistsSongs(){

    }

    public PlaylistsSongs(final Long playlistId, final Long songId) {
        this.playlistId = playlistId;
        this.songId = songId;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setPlaylistId(final Long playlistId) {
        this.playlistId = playlistId;
    }

    public void setSongId(final Long songId) {
        this.songId = songId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistsSongs that = (PlaylistsSongs) o;
        return Objects.equals(playlistId, that.playlistId) && Objects.equals(songId, that.songId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, songId);
    }
}
