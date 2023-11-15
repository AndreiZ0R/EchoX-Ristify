package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.User;
import com.ristify.ristifybackend.utils.AbstractUnitTest;
import com.ristify.ristifybackend.utils.Randoms;
import com.ristify.ristifybackend.utils.UserUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class UserRepositoryTest extends AbstractUnitTest<User> {
    private static final String startTime = "1800-01-01 00:00:00";
    private static final String endTime = "1900-01-01 00:00:00";

    @Autowired
    private UserRepository repository;

    @Test
    void findByUsername_userInRepository_savesAndFindsUser() {
        // Given
        User user = UserUtils.createRandomUser();
        repository.save(user);

        // When
        Optional<User> result = repository.findByUsername(user.getUsername());

        // Then
        result.ifPresentOrElse(
                foundUser -> assertThatItemsAreEqual(foundUser, user),
                this::assertThatFails);
    }

    @Test
    void findByUsername_userNotInRepository_returnsEmpty() {
        // Given & When
        Optional<User> result = repository.findByUsername(Randoms.alphabetic());

        // Then
        result.ifPresent(this::assertThatFails);
    }

    @Test
    void findByEmail_userInRepository_returnsUser() {
        // Given
        User user = UserUtils.createRandomUser();
        repository.save(user);

        // When
        Optional<User> result = repository.findByEmail(user.getEmail());

        // Then
        result.ifPresentOrElse(
                foundUser -> assertThatItemsAreEqual(foundUser, user),
                this::assertThatFails);
    }

    @Test
    void findByEmail_userNotInRepository_returnsEmpty() {
        // Given & When
        Optional<User> result = repository.findByEmail(Randoms.alphabetic());

        // Then
        result.ifPresent(this::assertThatFails);
    }

    @Test
    void findByFirstName_userInRepository_returnsUser() {
        // Given
        User user = UserUtils.createRandomUser();
        repository.save(user);

        // When
        Optional<User> result = repository.findByFirstName(user.getFirstName());

        // Then
        result.ifPresentOrElse(
                foundUser -> assertThatItemsAreEqual(foundUser, user),
                this::assertThatFails);
    }

    @Test
    void findByFirstName_userNotInRepository_returnsEmpty() {
        // Given & When
        Optional<User> result = repository.findByFirstName(Randoms.alphabetic());

        // Then
        result.ifPresent(this::assertThatFails);
    }

    @Test
    void findByLastName_userInRepository_returnsUser() {
        // Given
        User user = UserUtils.createRandomUser();
        repository.save(user);

        // When
        Optional<User> result = repository.findByLastName(user.getLastName());

        // Then
        result.ifPresentOrElse(
                foundUser -> assertThatItemsAreEqual(foundUser, user),
                this::assertThatFails);
    }

    @Test
    void findByLastName_userNotInRepository_returnsEmpty() {
        // Given & When
        Optional<User> result = repository.findByLastName(Randoms.alphabetic());

        // Then
        result.ifPresent(this::assertThatFails);
    }

    @Test
    void findAllByCountry_usersMatchCriteria_returnsUsersList() {
        // Given
        String country = Randoms.alphabetic();
        List<User> users = List.of(
                UserUtils.createRandomUserWithCountry(country),
                UserUtils.createRandomUserWithCountry(country),
                UserUtils.createRandomUserWithCountry(country));
        repository.saveAll(users);

        // When
        List<User> result = repository.findAllByCountry(country);

        // Then
        assertThatItemsAreEqual(result, users);
    }

    @Test
    void findAllByCountry_notAllUsersMatchCriteria_returnsCorrectUsers() {
        // Given
        String country = Randoms.alphabetic();
        User user1 = UserUtils.createRandomUserWithCountry(country);
        User user2 = UserUtils.createRandomUserWithCountry(country);
        repository.saveAll(List.of(user1, UserUtils.createRandomUser(), user2));

        // When
        List<User> result = repository.findAllByCountry(country);

        // Then
        assertThatItemsAreEqual(result, List.of(user1, user2));
    }

    @Test
    void findAllByCountry_noUserMatchesCriteria_returnsEmptyList() {
        // Given
        String country = Randoms.alphabetic();
        repository.saveAll(List.of(UserUtils.createRandomUser(), UserUtils.createRandomUser()));

        // When
        List<User> result = repository.findAllByCountry(country);

        // Then
        assertThatItemsAreEqual(result, List.of());
    }

    @Test
    void findByUsernameContaining_usersMatchCriteria_returnsUsersList() {
        // Given
        String pattern = Randoms.alphabetic(4);
        List<User> users = List.of(
                UserUtils.createRandomUser(pattern + Randoms.alphabetic(5)),
                UserUtils.createRandomUser(pattern + Randoms.alphabetic(5)),
                UserUtils.createRandomUser(pattern + Randoms.alphabetic(5)));
        repository.saveAll(users);

        // When
        List<User> result = repository.findByUsernameContaining(pattern);

        // Then
        assertThatItemsAreEqual(result, users);
    }

    @Test
    void findByUsernameContaining_notAllUsersMatchCriteria_returnsCorrectUsers() {
        // Given
        String pattern = Randoms.alphabetic(4);
        User user = UserUtils.createRandomUser(pattern + Randoms.alphabetic(5));
        repository.saveAll(List.of(user, UserUtils.createRandomUser(), UserUtils.createRandomUser()));

        // When
        List<User> result = repository.findByUsernameContaining(pattern);

        // Then
        assertThatItemsAreEqual(result, List.of(user));
    }

    @Test
    void findByUsernameContaining_noUserMatchesCriteria_returnsEmptyList() {
        // Given
        String pattern = Randoms.alphabetic(4);
        repository.saveAll(List.of(UserUtils.createRandomUser(), UserUtils.createRandomUser()));

        // When
        List<User> result = repository.findByUsernameContaining(pattern);

        // Then
        assertThatItemsAreEqual(result, List.of());
    }

    @Test
    void findMultipleById_usersMatchCriteria_returnsUsersList() {
        // Given
        User user1 = UserUtils.createRandomUser();
        User user2 = UserUtils.createRandomUser();
        repository.saveAll(List.of(user1, user2));

        // When
        List<User> result = repository.findMultipleById(List.of(user1.getUserId(), user2.getUserId()));

        // Then
        assertThatItemsAreEqual(result, List.of(user1, user2));
    }

    @Test
    void findMultipleById_onlyOneUserMatchesCriteria_returnsCorrectUsers() {
        // Given
        User user1 = UserUtils.createRandomUser();
        repository.saveAll(List.of(user1, UserUtils.createRandomUser()));

        // When
        List<User> result = repository.findMultipleById(List.of(user1.getUserId(), Randoms.randomPositiveInteger()));

        // Then
        assertThatItemsAreEqual(result, List.of(user1));
    }

    @Test
    void findMultipleById_noUserMatchesCriteria_returnsEmptyList() {
        // Given
        repository.saveAll(List.of(UserUtils.createRandomUser(), UserUtils.createRandomUser()));

        // When
        List<User> result = repository.findMultipleById(List.of(Randoms.randomPositiveInteger(), Randoms.randomPositiveInteger()));

        // Then
        assertThatItemsAreEqual(result, List.of());
    }

    @Test
    void deleteUserById_userIsInRepository_deletesUser() {
        // Given
        User user = UserUtils.createRandomUser();
        repository.save(user);

        // When
        Optional<User> result = repository.deleteUserById(user.getUserId());

        // Then
        result.ifPresentOrElse(
                deletedUser -> assertThatItemsAreEqual(deletedUser, user),
                this::assertThatFails);
    }

    @Test
    void deleteUserById_userIsNotInRepository_deletesUser() {
        // Given
        repository.save(UserUtils.createRandomUser());

        // When
        Optional<User> result = repository.deleteUserById(Randoms.randomPositiveInteger());

        // Then
        result.ifPresent(this::assertThatFails);
    }
}