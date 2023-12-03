package com.ristify.ristifybackend.dto;

import com.ristify.ristifybackend.dto.friendship.FriendshipDTO;
import com.ristify.ristifybackend.dto.playlist.PlaylistDTO;
import com.ristify.ristifybackend.dto.playlistsong.PlaylistSongDTO;
import com.ristify.ristifybackend.dto.playlistsong.PlaylistWithSongsDTO;
import com.ristify.ristifybackend.dto.song.SongDTO;
import com.ristify.ristifybackend.dto.user.UserDTO;
import com.ristify.ristifybackend.models.user.Friendship;
import com.ristify.ristifybackend.models.playlist.Playlist;
import com.ristify.ristifybackend.models.playlist.PlaylistSong;
import com.ristify.ristifybackend.models.playlist.Song;
import com.ristify.ristifybackend.models.user.User;

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
                mapUserToDTO(friendship.getUser1()),
                mapUserToDTO(friendship.getUser2()),
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