package com.ristify.ristifybackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id", unique = true, nullable = false)
    private Integer songId;

    @Column(name = "song_name", nullable = false)
    private String songName;

    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @Column(name = "album_name", nullable = false)
    private String albumName;

    @Column(name = "url", unique = true, nullable = false)
    private String url;
}