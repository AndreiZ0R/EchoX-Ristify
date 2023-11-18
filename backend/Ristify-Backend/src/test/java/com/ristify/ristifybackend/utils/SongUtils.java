package com.ristify.ristifybackend.utils;

import com.ristify.ristifybackend.models.Song;

public class SongUtils {

    public static Song createRandomSong(final Integer id) {
        Song song = createRandomSong();
        song.setSongId(id);
        return song;
    }

    public static Song createRandomSongWithSongName(String songName) {
        Song song = createRandomSong();
        song.setSongName(songName);
        return song;
    }

    public static Song createRandomSongWithArtistName(String artistName) {
        Song song = createRandomSong();
        song.setArtistName(artistName);
        return song;
    }

    public static Song createRandomSongWithAlbumName(String albumName) {
        Song song = createRandomSong();
        song.setAlbumName(albumName);
        return song;
    }

    public static Song createRandomSong() {
        return createRandomSong(
                Randoms.randomPositiveInteger(),
                Randoms.alphabetic(),
                Randoms.alphabetic(),
                Randoms.alphabetic(),
                Randoms.alphabetic());
    }

    public static Song createRandomSong(
            final Integer songId,
            final String songName,
            final String artistName,
            final String albumName,
            final String url) {
        return new Song(songId, songName, artistName, albumName, url);
    }
}
