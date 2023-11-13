package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.User;
import com.ristify.ristifybackend.utils.UserUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void beforeUserRepositoryTest() {
        // userRepository = mock(UserRepository.class);
    }

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // Given
        User user = UserUtils.createRandomUser();
        userRepository.save(user);

        // When
        Optional<User> found = userRepository.findByUsername(user.getUsername());

        // Then
        assertTrue(found.isPresent());
        assertEquals(user.getUsername(), found.get().getUsername());
    }

    @Test
    public void whenFindByEmail_thenReturnUser() {
        // Given
        User user = UserUtils.createRandomUser();

        // When
        Optional<User> found = userRepository.findByEmail(user.getEmail());

        // Then
        assertTrue(found.isPresent());
        assertEquals(user.getEmail(), found.get().getEmail());
    }

    @Test
    public void whenFindByFirstName_thenReturnUser() {
        // Given
        User user = UserUtils.createRandomUser();

        // When
        Optional<User> found = userRepository.findByFirstName(user.getEmail());

        // Then
        assertTrue(found.isPresent());
        assertEquals(user.getFirstName(), found.get().getFirstName());
    }

    @Test
    public void whenFindByLastName_thenReturnUser() {
        // Given
        User user = UserUtils.createRandomUser();

        // When
        Optional<User> found = userRepository.findByLastName(user.getEmail());

        // Then
        assertTrue(found.isPresent());
        assertEquals(user.getLastName(), found.get().getLastName());
    }

    @Test
    public void whenFindByCountry_thenReturnUsers() {
        // Given
        User user = UserUtils.createRandomUser();

        // When
        List<User> users = userRepository.findAllByCountry(user.getCountry());

        // Then
        assertFalse(users.isEmpty());
        assertTrue(users.contains(user));
    }

    @Test
    public void whenDeleteByUserId_thenUserShouldBeDeleted() {
        // Given
        User user = UserUtils.createRandomUser();

        // When
        userRepository.deleteByUserId(user.getUserId());
        Optional<User> deletedUser = userRepository.findByUserId(user.getUserId());

        // Then
        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testFindByUsernameContaining() {
        // Given
        String pattern = "user";
        User user = UserUtils.createRandomUser(pattern + "123");
        List<User> mockUsers = List.of(user, UserUtils.createRandomUser());
        when(userRepository.findByUsernameContaining(pattern)).thenReturn(mockUsers);

        // When
        List<User> foundUsers = userRepository.findByUsernameContaining(pattern);

        // Then
        assertThat(foundUsers, hasItems(user));
    }

//    @Test
//    public void testFindByCreatedAtBetween() {
//        Timestamp startDate = Timestamp.valueOf("2023-01-01 00:00:00");
//        Timestamp endDate = Timestamp.valueOf("2023-12-31 23:59:59");
//        List<User> mockUsers = Arrays.asList(
//                new User(
//                        1002,
//                        "username",
//                        "password",
//                        "FirstName",
//                        "LastName",
//                        "email@example.com",
//                        "Country",
//                        Timestamp.valueOf("2023-06-15 12:00:00"),
//                        new Timestamp(System.currentTimeMillis()),
//                        new Date())
//        );
//
//        when(userRepository.findByCreatedAtBetween(startDate, endDate)).thenReturn(mockUsers);
//        List<User> foundUsers = userRepository.findByCreatedAtBetween(startDate, endDate);
//        assertNotNull(foundUsers);
//        assertFalse(foundUsers.isEmpty());
//        assertTrue(foundUsers.stream().allMatch(user -> user.getCreatedAt().after(startDate) && user.getCreatedAt().before(endDate)));
//    }
//
//    @Test
//    public void testFindByLastLoginBefore() {
//        Timestamp date = Timestamp.valueOf("2023-09-01 00:00:00");
//        List<User> mockUsers = Arrays.asList(
//                new User(
//                        1003,
//                        "username",
//                        "password",
//                        "FirstName",
//                        "LastName",
//                        "email@example.com",
//                        "Country",
//                        new Timestamp(System.currentTimeMillis()),
//                        Timestamp.valueOf("2023-08-15 12:00:00"),
//                        new Date())
//        );
//
//        when(userRepository.findByLastLoginBefore(date)).thenReturn(mockUsers);
//        List<User> foundUsers = userRepository.findByLastLoginBefore(date);
//        assertNotNull(foundUsers);
//        assertFalse(foundUsers.isEmpty());
//        assertTrue(foundUsers.stream().allMatch(user -> user.getLastLogin().before(date)));
//    }
}