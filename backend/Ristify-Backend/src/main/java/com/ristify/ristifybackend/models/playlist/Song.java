package com.ristify.ristifybackend.models.playlist;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "songs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@OnDelete(action = OnDeleteAction.CASCADE)
public class Song {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id", unique = true, nullable = false, insertable = false)
    private Integer songId;

    @NonNull
    @Column(name = "song_name", nullable = false)
    private String songName;

    @NonNull
    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @NonNull
    @Column(name = "album_name", nullable = false)
    private String albumName;

    @NonNull
    @Column(name = "url", unique = true, nullable = false)
    private String url;
}