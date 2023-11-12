package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.PlaylistSong;
import com.ristify.ristifybackend.models.keys.PlaylistSongKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, PlaylistSongKey> {

    @Query(value = "select * from PlaylistSongs ps where ps.playlist_id=:playlistId", nativeQuery = true)
    List<PlaylistSong> getAllSongsFromPlaylist(final Integer playlistId);
}