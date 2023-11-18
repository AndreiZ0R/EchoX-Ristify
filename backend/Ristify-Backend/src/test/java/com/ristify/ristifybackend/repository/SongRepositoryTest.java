package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.Song;
import com.ristify.ristifybackend.utils.AbstractUnitTest;
import com.ristify.ristifybackend.utils.Randoms;
import com.ristify.ristifybackend.utils.SongUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class SongRepositoryTest extends AbstractUnitTest<Song> {

    @Autowired
    private SongRepository repository;

    @Test
    void findBySongName_songInRepository_saveAndFindsSong() {
        // Given
        Song song = SongUtils.createRandomSongWithSongName(Randoms.alphabetic());
        repository.save(song);

        // When
        Optional<Song> result = repository.findByName(song.getSongName());

        // Then
        result.ifPresentOrElse(
                foundSong -> assertThatItemsAreEqual(foundSong, song),
                this::assertThatFails);
    }

    @Test
    void findBySongName_songNotInRepository_returnsEmpty() {
        // Given & When
        Optional<Song> result = repository.findByName(Randoms.alphabetic());

        // Then
        result.ifPresent(this::assertThatFails);
    }

    @Test
    void findByArtist_songsInRepository_returnsSongs() {
        // Given
        String artistName = Randoms.alphabetic();
        Song song1 = SongUtils.createRandomSongWithArtistName(artistName);
        Song song2 = SongUtils.createRandomSongWithArtistName(artistName);
        List<Song> expectedResult = List.of(song1, song2);
        repository.save(song1);
        repository.save(song2);

        // When
        List<Song> result = repository.findByArtist(artistName);

        // Then
        assertThatItemsAreEqual(result, expectedResult);
    }

    @Test
    void findByArtist_songsNotInRepository_returnsEmpty() {
        // Given & When
        String artistName = Randoms.alphabetic();
        List<Song> result = repository.findByArtist(artistName);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void findByAlbum_songsInRepository_returnsSongs() {
        // Given
        String albumName = Randoms.alphabetic();
        Song song1 = SongUtils.createRandomSongWithAlbumName(albumName);
        Song song2 = SongUtils.createRandomSongWithAlbumName(albumName);
        repository.save(song1);
        repository.save(song2);
        List<Song> expectedResult = List.of(song1, song2);

        // When
        List<Song> result = repository.findByAlbum(albumName);

        // Then
        assertThatItemsAreEqual(result, expectedResult);
    }

    @Test
    void findByAlbum_songsNotInRepository_returnsEmpty() {
        // Given & When
        String albumName = Randoms.alphabetic();
        List<Song> result = repository.findByAlbum(albumName);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void deleteSongById_songIsInRepository_deletesSong() {
        // Given
        Song song = SongUtils.createRandomSong();
        repository.save(song);

        // When
        Optional<Song> result = repository.deleteSongById(song.getSongId());

        // Then
        result.ifPresentOrElse(
                deletedSong -> assertThatItemsAreEqual(deletedSong, song),
                this::assertThatFails);
    }

    @Test
    void deleteUserById_userIsNotInRepository_deletesUser() {
        // Given
        repository.save(SongUtils.createRandomSong());

        // When
        Optional<Song> result = repository.deleteSongById(Randoms.randomPositiveInteger());

        // Then
        result.ifPresent(this::assertThatFails);
    }
}