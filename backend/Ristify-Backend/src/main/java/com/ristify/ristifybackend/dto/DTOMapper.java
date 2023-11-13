package com.ristify.ristifybackend.dto;

import com.ristify.ristifybackend.dto.friendship.FriendshipDTO;
import com.ristify.ristifybackend.dto.playlist.PlaylistDTO;
import com.ristify.ristifybackend.dto.playlistsong.PlaylistSongDTO;
import com.ristify.ristifybackend.dto.playlistsong.PlaylistWithSongsDTO;
import com.ristify.ristifybackend.dto.song.SongDTO;
import com.ristify.ristifybackend.dto.user.UserDTO;
import com.ristify.ristifybackend.models.Friendship;
import com.ristify.ristifybackend.models.Playlist;
import com.ristify.ristifybackend.models.PlaylistSong;
import com.ristify.ristifybackend.models.Song;
import com.ristify.ristifybackend.models.User;

import java.util.List;
import java.util.stream.Collectors;

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
                playlist.getPlaylistId(),
                playlist.getName(),
                DTOMapper.mapUserToDTO(playlist.getUser())
        );
    }

    public static PlaylistSongDTO mapPlaylistSongDTO(final PlaylistSong playlistSong) {
        return new PlaylistSongDTO(
                playlistSong.getPlaylist().getPlaylistId(),
                mapSongToDTO(playlistSong.getSong())
        );
    }

    public static PlaylistWithSongsDTO mapPlaylistSongDTO(final Playlist playlist, final List<Song> songs) {
        return new PlaylistWithSongsDTO(
                playlist.getPlaylistId(),
                playlist.getName(),
                songs.stream().map(DTOMapper::mapSongToDTO).collect(Collectors.toList())
        );
    }
}