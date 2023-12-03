package com.ristify.ristifybackend.service;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.user.UserDTO;
import com.ristify.ristifybackend.models.user.User;
import com.ristify.ristifybackend.repository.user.UserRepository;
import com.ristify.ristifybackend.service.user.UserService;
import com.ristify.ristifybackend.utils.AbstractUnitTest;
import com.ristify.ristifybackend.utils.Randoms;
import com.ristify.ristifybackend.utils.UserUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest extends AbstractUnitTest<User> {
    private UserService sut;
    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void beforeUserServiceTest() {
        repository = mock(UserRepository.class);
        sut = new UserService(repository);

    }

    @Test
    void getAllUsers_hasUsers_returnsTheUsersList() {
        // Given
        List<User> users = List.of(UserUtils.createRandomUser(), UserUtils.createRandomUser(), UserUtils.createRandomUser());
        when(repository.findAll()).thenReturn(users);

        // When
        List<UserDTO> response = sut.getAllUsers();

        // Then
        List<UserDTO> expectedUsers = users.stream().map(DTOMapper::mapUserToDTO).toList();
        verify(repository).findAll();
        assertThat(response, equalTo(expectedUsers));
    }

    @Test
    void getAllUsers_doesNotHaveUsers_returnsEmpty() {
        // Given
        when(repository.findAll()).thenReturn(List.of());

        // When
        List<UserDTO> response = sut.getAllUsers();

        // Then
        verify(repository).findAll();
        assertThat(response.isEmpty(), equalTo(true));
    }

    @Test
    void findById_idIsValid_returnsUser() {
        // Given
        Integer id = Randoms.randomPositiveInteger();
        User user = UserUtils.createRandomUser(id);
        when(repository.findById(id)).thenReturn(Optional.of(user));

        // When
        Optional<UserDTO> response = sut.findById(id);

        // Then
        verify(repository).findById(id);
        response.ifPresentOrElse(
                userDTO -> assertThatUserDTOisValid(userDTO, user),
                this::assertThatFails);
    }

    @Test
    void findById_idIsNotValid_returnsEmptyOptional() {
        // Given
        Integer id = Randoms.randomPositiveInteger();

        // When
        Optional<UserDTO> response = sut.findById(id);

        // Then
        verify(repository).findById(id);
        response.ifPresent(this::assertThatFails);
    }

    @Test
    void findByUsername_usernameIsValid_returnsUser() {
        // Given
        String username = Randoms.alphabetic();
        User user = UserUtils.createRandomUser(username);
        when(repository.findByUsername(username)).thenReturn(Optional.of(user));

        // When
        Optional<UserDTO> response = sut.findByUsername(username);

        // Then
        verify(repository).findByUsername(username);
        response.ifPresentOrElse(
                userDTO -> assertThatUserDTOisValid(userDTO, user),
                this::assertThatFails);
    }

    @Test
    void findByUsername_usernameIsNotValid_returnsUser() {
        // Given
        String username = Randoms.alphabetic();

        // When
        Optional<UserDTO> response = sut.findByUsername(username);

        // Then
        verify(repository).findByUsername(username);
        response.ifPresent(this::assertThatFails);
    }

    //TODO: move to register
//    @Test
//    void saveUser_userIsValid_savesUser() {
//        // Given
//        User user = UserUtils.createRandomUser();
//        when(repository.save(user)).thenReturn(user);
//        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
//
//        // When
//        Optional<UserDTO> response = sut.saveUser(user);
//
//        // Then
//        verify(passwordEncoder).encode(user.getPassword());
//        verify(repository).save(user);
//        response.ifPresentOrElse(
//                userDTO -> assertThatUserDTOisValid(userDTO, user),
//                this::assertThatFails);
//    }

    //TODO: move to register
//    @Test
//    void saveUser_userIsNull_doesNotSaveUser() {
//        // Given & When
//        Optional<UserDTO> response = sut.saveUser(null);
//
//        // Then
//        verify(passwordEncoder, never()).encode(any());
//        verify(repository, never()).save(any());
//        response.ifPresent(this::assertThatFails);
//    }

    @Test
    void deleteUserById_idIsValid_deletesUser() {
        // Given
        Integer id = Randoms.randomPositiveInteger();
        User user = UserUtils.createRandomUser(id);
        when(repository.deleteUserById(id)).thenReturn(Optional.of(user));

        // When
        Optional<UserDTO> response = sut.deleteUserById(id);

        // Then
        verify(repository).deleteUserById(id);
        response.ifPresentOrElse(
                userDTO -> assertThatUserDTOisValid(userDTO, user),
                this::assertThatFails);
    }

    @Test
    void deleteUserById_idIsNotValid_callsDeleteButNothingHappens() {
        // Given
        Integer id = Randoms.randomPositiveInteger();

        // When
        Optional<UserDTO> response = sut.deleteUserById(id);

        // Then
        verify(repository).deleteUserById(id);
        response.ifPresent(this::assertThatFails);
    }

    private void assertThatUserDTOisValid(final UserDTO userDTO, final User user) {
        assertThat(userDTO, equalTo(DTOMapper.mapUserToDTO(user)));
    }
}