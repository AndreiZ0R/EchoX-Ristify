package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.user.Friendship;
import com.ristify.ristifybackend.repository.user.FriendshipRepository;
import com.ristify.ristifybackend.repository.user.UserRepository;
import com.ristify.ristifybackend.utils.AbstractUnitTest;
import com.ristify.ristifybackend.utils.FriendshipUtils;
import com.ristify.ristifybackend.utils.Randoms;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
class FriendshipRepositoryTest extends AbstractUnitTest<Friendship> {

    @Autowired
    private FriendshipRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getAllFriends_friendsExist_returnsFriendsList() {
        // Given
        Integer userId = Randoms.randomPositiveInteger();
        List<Friendship> friendships = List.of(
                createFriendshipInSpace(userId),
                createFriendshipInSpace(userId),
                createFriendshipInSpace());

        // When
        List<Friendship> result = repository.getAllFriendsForId(userId);
        System.out.println("====== RES: " + result);

        // Then
        assertThatItemsAreEqual(result, friendships);
    }


    private Friendship createFriendshipInSpace() {
        return createFriendshipInSpace(Randoms.randomPositiveInteger());
    }

    private Friendship createFriendshipInSpace(final Integer userId) {
        Friendship friendship = FriendshipUtils.createRandomFriendship(userId);
        userRepository.save(friendship.getUser1());
        userRepository.save(friendship.getUser2());
        repository.save(friendship);

        return friendship;
    }


}