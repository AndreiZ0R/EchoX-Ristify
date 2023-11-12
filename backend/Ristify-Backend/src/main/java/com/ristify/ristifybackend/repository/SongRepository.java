package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query(value = "select s from Song s where s.songName=:name")
    Optional<Song> findByName(final String name);

    @Query(value = "select s from Song s where s.artistName=:artistName")
    Optional<Song> findByArtist(final String artistName);

    @Query(value = "select s from Song s where s.albumName=:albumName")
    Optional<Song> findByAlbum(final String albumName);
}
