package com.ristify.ristifybackend.service;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.friendship.FriendshipDTO;
import com.ristify.ristifybackend.models.user.Friendship;
import com.ristify.ristifybackend.repository.user.FriendshipRepository;
import com.ristify.ristifybackend.repository.user.UserRepository;
import com.ristify.ristifybackend.service.user.FriendshipService;
import com.ristify.ristifybackend.utils.AbstractUnitTest;
import com.ristify.ristifybackend.utils.FriendshipUtils;
import com.ristify.ristifybackend.utils.Randoms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FriendshipServiceTest extends AbstractUnitTest<Friendship> {
    private FriendshipService sut;
    private FriendshipRepository friendshipRepository;
    private UserRepository userRepository;

    @BeforeEach
    void beforeFriendshipServiceTest() {
        userRepository = mock(UserRepository.class);
        friendshipRepository = mock(FriendshipRepository.class);
        sut = new FriendshipService(friendshipRepository, userRepository);
    }

    @Test
    void getAllFriendships_hasFriendships_returnsFriendshipsList() {
        // Given
        List<Friendship> friendships = List.of(FriendshipUtils.createRandomFriendship(), FriendshipUtils.createRandomFriendship());
        when(friendshipRepository.findAll()).thenReturn(friendships);

        // When
        List<FriendshipDTO> response = sut.getAllFriendships();

        // Then
        List<FriendshipDTO> expectedFriendships = friendships.stream().map(DTOMapper::mapFriendshipToDTO).toList();
        verify(friendshipRepository).findAll();
        assertThat(response, equalTo(expectedFriendships));
    }

    @Test
    void getAllFriendships_doesNotHaveFriendships_returnsEmptyList() {
        // Given
        when(friendshipRepository.findAll()).thenReturn(List.of());

        // When
        List<FriendshipDTO> response = sut.getAllFriendships();

        // Then
        verify(friendshipRepository).findAll();
        assertThat(response.isEmpty(), equalTo(true));
    }

    @Test
    void getAllFriendsForId_hasFriends_returnsFriendshipList() {
        // Given
        Integer userId = Randoms.randomPositiveInteger();
        List<Friendship> friendships = List.of(
                FriendshipUtils.createRandomFriendship(userId),
                FriendshipUtils.createRandomFriendship(userId),
                FriendshipUtils.createRandomFriendship());
        when(friendshipRepository.getAllFriendsForId(userId)).thenReturn(List.of(friendships.get(0), friendships.get(1)));

        // When
        List<FriendshipDTO> response = sut.getAllFriendsForId(userId);

        // Then
        List<FriendshipDTO> expectedFriendships = friendships.stream().map(DTOMapper::mapFriendshipToDTO).limit(2).toList();
        verify(friendshipRepository).getAllFriendsForId(userId);
        assertThat(response, equalTo(expectedFriendships));
    }

    @Test
    void getAllFriendsForId_doesNotHaveFriends_returnsEmptyList() {
        // Given
        Integer userId = Randoms.randomPositiveInteger();
        when(friendshipRepository.getAllFriendsForId(userId)).thenReturn(List.of());

        // When
        List<FriendshipDTO> response = sut.getAllFriendsForId(userId);

        // Then
        verify(friendshipRepository).getAllFriendsForId(userId);
        assertThat(response.isEmpty(), equalTo(true));
    }
}