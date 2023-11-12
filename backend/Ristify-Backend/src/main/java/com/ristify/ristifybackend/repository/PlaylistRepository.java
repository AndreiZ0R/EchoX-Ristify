package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    @Query(value = "select p from Playlist p where p.name=:name")
    Optional<Playlist> getByName(final String name);

    @Query(value = "select p from Playlist p where p.user.userId=:userId")
    List<Playlist> getAllUserPlaylists(final Integer userId);
}