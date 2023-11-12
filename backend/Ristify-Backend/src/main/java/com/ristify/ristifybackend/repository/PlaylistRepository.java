package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.Playlist;
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

    @Query(value = "select p from Playlist p where p.user.userId=:userId")
    List<Playlist> getAllUserPlaylists(final Integer userId);

    @Query(value = "select p from Playlist p where p.name=:name")
    Optional<Playlist> findByName(final String name);

    @Transactional
    @Query(value = "delete from Playlist u where u.playlistId=:id")
    void deletePlaylistById(final Integer id);
}