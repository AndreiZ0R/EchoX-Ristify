package com.ristify.ristifybackend.dto;

import com.ristify.ristifybackend.dto.friendship.FriendshipDTO;
import com.ristify.ristifybackend.dto.playlist.PlaylistDTO;
import com.ristify.ristifybackend.dto.song.SongDTO;
import com.ristify.ristifybackend.dto.user.UserDTO;
import com.ristify.ristifybackend.models.Friendship;
import com.ristify.ristifybackend.models.Playlist;
import com.ristify.ristifybackend.models.Song;
import com.ristify.ristifybackend.models.User;

public class DTOMapper {
    public static UserDTO mapUserToDTO(final User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCountry(),
                user.getCreatedAt(),
                user.getBirthDate());
    }

    public static FriendshipDTO mapFriendshipToDTO(final Friendship friendship) {
        return new FriendshipDTO(
                mapUserToDTO(friendship.getUserId1()),
                mapUserToDTO(friendship.getUserId2()),
                friendship.getCreatedAt());
    }

    public static SongDTO mapSongToDTO(final Song song) {
        return new SongDTO(
                song.getSongId(),
                song.getSongName(),
                song.getArtistName(),
                song.getAlbumName()
        );
    }

    public static PlaylistDTO mapPlaylistToDTO(final Playlist playlist) {
        return new PlaylistDTO(
                DTOMapper.mapUserToDTO(playlist.getUser()),
                playlist.getName()
        );
    }
}