package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(
                1000,
                "username",
                "password",
                "firstName",
                "lastName",
                "email",
                "country",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                new Date()
        );
        userRepository.save(user);
    }

    @Test
    public void whenFindByUsername_thenReturnUser() {
        Optional<User> found = userRepository.findByUsername(user.getUsername());

        assertTrue(found.isPresent());
        assertEquals(user.getUsername(), found.get().getUsername());
    }

    @Test
    public void whenFindByEmail_thenReturnUser() {
        Optional<User> found = userRepository.findByEmail(user.getEmail());

        assertTrue(found.isPresent());
        assertEquals(user.getEmail(), found.get().getEmail());
    }

    @Test
    public void whenFindByFirstName_thenReturnUser() {
        Optional<User> found = userRepository.findByFirstName(user.getEmail());

        assertTrue(found.isPresent());
        assertEquals(user.getFirstName(), found.get().getFirstName());
    }

    @Test
    public void whenFindByLastName_thenReturnUser() {
        Optional<User> found = userRepository.findByLastName(user.getEmail());

        assertTrue(found.isPresent());
        assertEquals(user.getLastName(), found.get().getLastName());
    }

    @Test
    public void whenFindByCountry_thenReturnUsers() {
        List<User> users = userRepository.findAllByCountry(user.getCountry());

        assertFalse(users.isEmpty());
        assertTrue(users.contains(user));
    }

    @Test
    public void whenDeleteByUserId_thenUserShouldBeDeleted() {
        userRepository.deleteByUserId(user.getUserId());
        Optional<User> deletedUser = userRepository.findByUserId(user.getUserId());

        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testFindByUsernameContaining() {
        String pattern = "user";
        List<User> mockUsers = Arrays.asList(
                new User(
                        1001,
                        "username1",
                        "password",
                        "FirstName",
                        "LastName",
                        "email1@example.com",
                        "Country",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis()),
                        new Date()),
                new User(
                        1002,
                        "user123",
                        "password",
                        "FirstName",
                        "LastName",
                        "email2@example.com",
                        "Country",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis()),
                        new Date())
        );

        when(userRepository.findByUsernameContaining(pattern)).thenReturn(mockUsers);
        List<User> foundUsers = userRepository.findByUsernameContaining(pattern);
        assertNotNull(foundUsers);
    }

    @Test
    public void testFindByCreatedAtBetween() {
        Timestamp startDate = Timestamp.valueOf("2023-01-01 00:00:00");
        Timestamp endDate = Timestamp.valueOf("2023-12-31 23:59:59");
        List<User> mockUsers = Arrays.asList(
                new User(
                        1002,
                        "username",
                        "password",
                        "FirstName",
                        "LastName",
                        "email@example.com",
                        "Country",
                        Timestamp.valueOf("2023-06-15 12:00:00"),
                        new Timestamp(System.currentTimeMillis()),
                        new Date())
        );

        when(userRepository.findByCreatedAtBetween(startDate, endDate)).thenReturn(mockUsers);
        List<User> foundUsers = userRepository.findByCreatedAtBetween(startDate, endDate);
        assertNotNull(foundUsers);
        assertFalse(foundUsers.isEmpty());
        assertTrue(foundUsers.stream().allMatch(user -> user.getCreatedAt().after(startDate) && user.getCreatedAt().before(endDate)));
    }

    @Test
    public void testFindByLastLoginBefore() {
        Timestamp date = Timestamp.valueOf("2023-09-01 00:00:00");
        List<User> mockUsers = Arrays.asList(
                new User(
                        1003,
                        "username",
                        "password",
                        "FirstName",
                        "LastName",
                        "email@example.com",
                        "Country",
                        new Timestamp(System.currentTimeMillis()),
                        Timestamp.valueOf("2023-08-15 12:00:00"),
                        new Date())
        );

        when(userRepository.findByLastLoginBefore(date)).thenReturn(mockUsers);
        List<User> foundUsers = userRepository.findByLastLoginBefore(date);
        assertNotNull(foundUsers);
        assertFalse(foundUsers.isEmpty());
        assertTrue(foundUsers.stream().allMatch(user -> user.getLastLogin().before(date)));
    }
}