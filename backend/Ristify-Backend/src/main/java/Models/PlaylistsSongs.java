package Models;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Table(name = "playlistssongs")
@NoArgsConstructor
@Getter
public class PlaylistsSongs {
    @Column(nullable = false, name = "playlistId")
    @OneToOne(targetEntity = Playlists.class)
    private Long playlistId;
    @Column(nullable = false, unique = true, name = "songId")
    @OneToOne(targetEntity = Songs.class)
    private Long songId;

    public PlaylistsSongs(final Long playlistId, final Long songId) {
        this.playlistId = playlistId;
        this.songId = songId;
    }

    public void setPlaylistId(final Long playlistId) {
        this.playlistId = playlistId;
    }

    public void setSongId(final Long songId) {
        this.songId = songId;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistsSongs that = (PlaylistsSongs) o;
        return Objects.equals(playlistId, that.playlistId) && Objects.equals(songId, that.songId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, songId);
    }

    @Override
    public String toString() {
        return "PlaylistsSongs{" +
                "playlistId=" + playlistId +
                ", songId=" + songId +
                '}';
    }
}
