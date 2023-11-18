package com.ristify.ristifybackend.service;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.song.SongDTO;
import com.ristify.ristifybackend.models.Song;
import com.ristify.ristifybackend.repository.SongRepository;
import com.ristify.ristifybackend.utils.AbstractUnitTest;
import com.ristify.ristifybackend.utils.Randoms;
import com.ristify.ristifybackend.utils.SongUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SongServiceTest extends AbstractUnitTest<Song> {

    private SongService sut;
    private SongRepository repository;

    @BeforeEach
    void beforeSongServiceTest() {
        repository = mock(SongRepository.class);
        sut = new SongService(repository);
    }

    @Test
    void getAllSongs_hasSongs_returnsTheSongsList() {
        // Given
        List<Song> songs = List.of(SongUtils.createRandomSong(), SongUtils.createRandomSong(), SongUtils.createRandomSong());
        when(repository.findAll()).thenReturn(songs);

        // When
        List<SongDTO> response = sut.getAllSongs();

        // Then
        List<SongDTO> expectedSongs = songs.stream().map(DTOMapper::mapSongToDTO).toList();
        verify(repository).findAll();
        assertThat(response, equalTo(expectedSongs));
    }

    @Test
    void getAllSongs_doesNotHaveSongs_returnsEmpty() {
        // Given
        when(repository.findAll()).thenReturn(List.of());

        // When
        List<SongDTO> response = sut.getAllSongs();

        // Then
        verify(repository).findAll();
        assertThat(response.isEmpty(), equalTo(true));
    }


    @Test
    void findById_idIsValid_returnsSong() {
        // Given
        Integer id = Randoms.randomPositiveInteger();
        Song song = SongUtils.createRandomSong(id);
        when(repository.findById(id)).thenReturn(Optional.of(song));

        // When
        Optional<SongDTO> response = sut.findById(id);

        // Then
        verify(repository).findById(id);
        response.ifPresentOrElse(
                songDTO -> assertThatSongDTOisValid(songDTO, song),
                this::assertThatFails);
    }

    @Test
    void findById_idIsNotValid_returnsEmptyOptional() {
        // Given
        Integer id = Randoms.randomPositiveInteger();

        // When
        Optional<SongDTO> response = sut.findById(id);

        // Then
        verify(repository).findById(id);
        response.ifPresent(this::assertThatFails);
    }

    @Test
    void findByName_nameIsValid_returnsSong() {
        // Given
        String songName = Randoms.alphabetic();
        Song song = SongUtils.createRandomSongWithSongName(songName);
        when(repository.findByName(songName)).thenReturn(Optional.of(song));

        // When
        Optional<SongDTO> response = sut.findByName(songName);

        // Then
        verify(repository).findByName(songName);
        response.ifPresentOrElse(
                songDTO -> assertThatSongDTOisValid(songDTO, song),
                this::assertThatFails);
    }

    @Test
    void findByName_nameIsNotValid_returnsEmptyOptional() {
        // Given
        String songName = Randoms.alphabetic();

        // When
        Optional<SongDTO> response = sut.findByName(songName);

        // Then
        verify(repository).findByName(songName);
        response.ifPresent(this::assertThatFails);
    }

    @Test
    void findByArtist_artistIsValid_returnsSongs() {
        // Given
        String artistName = Randoms.alphabetic();
        List<Song> songs = List.of(SongUtils.createRandomSongWithArtistName(artistName), SongUtils.createRandomSongWithArtistName(artistName), SongUtils.createRandomSongWithArtistName(artistName));
        when(repository.findByArtist(artistName)).thenReturn(songs);

        // When
        List<SongDTO> response = sut.findByArtist(artistName);

        // Then
        List<SongDTO> expectedSongs = songs.stream().map(DTOMapper::mapSongToDTO).toList();
        verify(repository).findByArtist(artistName);
        assertThat(response, equalTo(expectedSongs));
    }

    @Test
    void findByArtist_artistIsNotValid_returnsEmpty() {
        // Given
        String artistName = Randoms.alphabetic();
        when(repository.findByArtist(artistName)).thenReturn(List.of());

        // When
        List<SongDTO> response = sut.findByArtist(artistName);

        // Then
        verify(repository).findByArtist(artistName);
        assertThat(response.isEmpty(), equalTo(true));
    }

    @Test
    void findByAlbum_albumIsValid_returnsSongs() {
        // Given
        String albumName = Randoms.alphabetic();
        List<Song> songs = List.of(SongUtils.createRandomSongWithAlbumName(albumName), SongUtils.createRandomSongWithAlbumName(albumName), SongUtils.createRandomSongWithAlbumName(albumName));
        when(repository.findByAlbum(albumName)).thenReturn(songs);

        // When
        List<SongDTO> response = sut.findByAlbum(albumName);

        // Then
        List<SongDTO> expectedSongs = songs.stream().map(DTOMapper::mapSongToDTO).toList();
        verify(repository).findByAlbum(albumName);
        assertThat(response, equalTo(expectedSongs));
    }

    @Test
    void findByAlbum_albumIsNotValid_returnsEmpty() {
        // Given
        String albumName = Randoms.alphabetic();
        when(repository.findByAlbum(albumName)).thenReturn(List.of());

        // When
        List<SongDTO> response = sut.findByAlbum(albumName);

        // Then
        verify(repository).findByAlbum(albumName);
        assertThat(response.isEmpty(), equalTo(true));
    }

    // TODO Only after the getPaginatedEntities is made generic for entities
    @Test
    void getPaginatedSongs() {
    }

    @Test
    void saveSong_songIsValid_savesSong() {
        // Given
        Song song= SongUtils.createRandomSong();
        when(repository.save(song)).thenReturn(song);

        // When
        Optional<SongDTO> response = sut.saveSong(song);

        // Then
        verify(repository).save(song);
        response.ifPresentOrElse(
                songDTO -> assertThatSongDTOisValid(songDTO, song),
                this::assertThatFails);
    }

    @Test
    void saveSong_songIsNull_doesNotSaveSong() {
        // Given & When
        Optional<SongDTO> response = sut.saveSong(null);

        // Then
        verify(repository, never()).save(any());
        response.ifPresent(this::assertThatFails);
    }

    @Test
    void deleteSongById_idIsValid_deletesSong() {
        // Given
        Integer id = Randoms.randomPositiveInteger();
        Song song = SongUtils.createRandomSong(id);
        when(repository.deleteSongById(id)).thenReturn(Optional.of(song));

        // When
        Optional<SongDTO> response = sut.deleteSongById(id);

        // Then
        verify(repository).deleteSongById(id);
        response.ifPresentOrElse(
                songDTO -> assertThatSongDTOisValid(songDTO, song),
                this::assertThatFails);
    }

    @Test
    void deleteSongById_idIsNotValid_callsDeleteButNothingHappens() {
        // Given
        Integer id = Randoms.randomPositiveInteger();

        // When
        Optional<SongDTO> response = sut.deleteSongById(id);

        // Then
        verify(repository).deleteSongById(id);
        response.ifPresent(this::assertThatFails);
    }

    private void assertThatSongDTOisValid(final SongDTO songDTO, final Song song) {
        assertThat(songDTO, equalTo(DTOMapper.mapSongToDTO(song)));
    }
}