package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.Song;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query(value = "select s from Song s where s.songName=:name")
    Optional<Song> findByName(final String name);

    @Query(value = "select s from Song s where s.artistName=:artistName")
    List<Song> findByArtist(final String artistName);

    @Query(value = "select s from Song s where s.albumName=:albumName")
    List<Song> findByAlbum(final String albumName);

    @Transactional
    @Query(value = "delete from Songs s where s.song_id=:id returning *", nativeQuery = true)
    Optional<Song> deleteSongById(final Integer id);
}
