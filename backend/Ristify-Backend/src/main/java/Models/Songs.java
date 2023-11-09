package Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "songs")
@NoArgsConstructor
@Getter
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "songName")
    private String songName;
    @Column(nullable = false, name = "artistName")
    private String artistName;
    @Column(name = "albumName")
    private String albumName;
    @Column(nullable = false)
    private String url;

    public Songs(final Long id,
                 final String songName,
                 final String artistName,
                 final String albumName,
                 final String url) {
        this.id = id;
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.url = url;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setSongName(final String songName) {
        this.songName = songName;
    }

    public void setArtistName(final String artistName) {
        this.artistName = artistName;
    }

    public void setAlbumName(final String albumName) {
        this.albumName = albumName;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Songs songs = (Songs) o;
        return Objects.equals(id, songs.id) &&
                Objects.equals(songName, songs.songName) &&
                Objects.equals(artistName, songs.artistName) &&
                Objects.equals(albumName, songs.albumName) &&
                Objects.equals(url, songs.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songName, artistName, albumName, url);
    }

    @Override
    public String toString() {
        return "Songs{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
