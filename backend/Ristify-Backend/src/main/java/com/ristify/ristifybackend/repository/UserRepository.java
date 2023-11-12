
package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User save(User user);

    void deleteByUserId(Integer userId);

    Optional<User> findByUserId(Integer userId);

    Optional<User> findByUsername(String username);

    Optional<User> findByFirstName(String firstName);

    Optional<User> findByLastName(String lastName);

    Optional<User> findByEmail(String email);

    List<User> findAllByCountry(String country);

    List<User> findByUsernameContaining(String pattern);

    List<User> findByCreatedAtBetween(Timestamp startDate, Timestamp endDate);

    List<User> findByLastLoginBefore(Timestamp date);
}
