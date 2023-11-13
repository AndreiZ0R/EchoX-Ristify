package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

//TODO: finish unimplemented methods - Andrei
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u from User u where u.username=:username")
    Optional<User> findByUsername(final String username);

    @Query(value = "select u from User u where u.firstName=:firstName")
    Optional<User> findByFirstName(final String firstName);

    @Query(value = "select u from User u where u.lastName=:lastName")
    Optional<User> findByLastName(final String lastName);

    @Query(value = "select u from User u where u.email=:email")
    Optional<User> findByEmail(final String email);

    @Query(value = "select u from User u where u.country=:country")
    List<User> findAllByCountry(final String country);

    @Query(value = "select u from User u WHERE u.username like %:pattern%")
    List<User> findByUsernameContaining(final String pattern);

    @Query(value = "select u from User u WHERE u.createdAt between :startDate and :endDate")
    List<User> findByCreatedAtBetween(final Timestamp startDate, final Timestamp endDate);

    @Query(value = "select u from User u where u.userId in :ids")
    List<User> findMultipleById(final List<Integer> ids);

    @Transactional
    @Query(value = "delete from Users u where u.user_id=:id returning *",nativeQuery = true)
    Optional<User> deleteUserById(final Integer id);
}