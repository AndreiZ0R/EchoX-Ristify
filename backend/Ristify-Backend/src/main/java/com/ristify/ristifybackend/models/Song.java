package com.ristify.ristifybackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "songs")
@NoArgsConstructor
@Getter
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(targetEntity = Playlist.class)
    @JoinColumn(name = "songId")
    private Long songId;

    @Column(nullable = false, name = "songName")
    private String songName;

    @Column(nullable = false, name = "artistName")
    private String artistName;

    @Column(name = "albumName")
    private String albumName;

    @Column(nullable = false)
    private String url;

    public Song(final Long songId, final String songName, final String artistName, final String albumName, final String url) {
        this.songId = songId;
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.url = url;
    }

    public void setSongId(final Long id) {
        this.songId = id;
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
        Song song = (Song) o;
        return Objects.equals(songId, song.songId) &&
                Objects.equals(songName, song.songName) &&
                Objects.equals(artistName, song.artistName) &&
                Objects.equals(albumName, song.albumName) &&
                Objects.equals(url, song.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songName, artistName, albumName, url);
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}