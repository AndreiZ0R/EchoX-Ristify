package com.ristify.ristifybackend.repository.playlist;

import com.ristify.ristifybackend.models.playlist.Playlist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into Playlists(user_id, name) VALUES (:userId, :name)", nativeQuery = true)
    void storePlaylist(final Integer userId, final String name);

    @Query(value = "select p from Playlist p where p.name=:name")
    Optional<Playlist> getByName(final String name);

    //TODO: implement this
    @Query(value = "select p from Playlist p where p.user.userId=:userId")
    List<Playlist> findAllPlaylistsForUser(final Integer userId);

    @Query(value = "select p from Playlist p where p.name=:name")
    Optional<Playlist> findByName(final String name);

    @Query(value = "select p from Playlist p where p.playlistId in :ids")
    List<Playlist> findMultipleById(final List<Integer> ids);

    @Transactional
    @Query(value = "delete from Playlists where playlist_id=:id returning *", nativeQuery = true)
    Optional<Playlist> deletePlaylistById(final Integer id);
}