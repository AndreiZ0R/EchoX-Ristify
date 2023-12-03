package com.ristify.ristifybackend.repository.playlist;

import com.ristify.ristifybackend.models.playlist.PlaylistSong;
import com.ristify.ristifybackend.models.keys.PlaylistSongKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, PlaylistSongKey> {

    @Query(value = "select * from Playlistsongs ps where ps.playlist_id=:playlistId", nativeQuery = true)
    List<PlaylistSong> findAllSongsFromPlaylist(final Integer playlistId);

    @Query(value = "select * from Playlistsongs ps where ps.song_id=:songId", nativeQuery = true)
    List<PlaylistSong> findAllPlaylistsContainingSong(final Integer songId);

    @Transactional
    @Query(value = "insert into Playlistsongs(playlist_id, song_id) values (:playlistId, :songId) returning *",
            nativeQuery = true)
    Optional<PlaylistSong> storePlaylistSong(final Integer playlistId, final Integer songId);

    @Transactional
    @Query(value = "delete from Playlistsongs ps where ps.playlist_id=:playlistId and ps.song_id=:songId returning *",
            nativeQuery = true)
    Optional<PlaylistSong> deleteSongFromPlaylist(final Integer playlistId, final Integer songId);
}