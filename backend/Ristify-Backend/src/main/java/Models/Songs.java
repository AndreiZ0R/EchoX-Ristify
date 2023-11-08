package Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "songs")
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String songName;
    @Column(nullable = false)
    private String artistName;
    @Column
    private String albumName;
    @Column(nullable = false)
    private String url;

    public Songs(){

    }

    public Songs(Long id, String songName, String artistName, String albumName, String url) {
        this.id = id;
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.url = url;
    }

    //Getters

    public Long getId() {
        return id;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getUrl() {
        return url;
    }

    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Songs songs = (Songs) o;
        return Objects.equals(id, songs.id) && Objects.equals(songName, songs.songName) && Objects.equals(artistName, songs.artistName) && Objects.equals(albumName, songs.albumName) && Objects.equals(url, songs.url);
    }

    //hashCode

    @Override
    public int hashCode() {
        return Objects.hash(id, songName, artistName, albumName, url);
    }
}
