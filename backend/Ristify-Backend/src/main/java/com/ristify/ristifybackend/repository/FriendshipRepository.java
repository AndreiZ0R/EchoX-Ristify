package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.Friendship;
import com.ristify.ristifybackend.models.keys.FriendshipKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, FriendshipKey> {

    @Query(value = "select * from Friendships f where f.user_id1=:userId or f.user_id2=:userId", nativeQuery = true)
    List<Friendship> getAllFriends(final Integer userId);

    @Query(value = "select * from Friendships f where f.user_id1=:id1 and f.user_id2=:id2", nativeQuery = true)
    Optional<Friendship> findFriendship(final Integer id1, final Integer id2);

    @Transactional
    @Query(value = "insert into Friendships(user_id1, user_id2) VALUES (:id1, :id2) returning *", nativeQuery = true)
    Optional<Friendship> storeFriendship(final Integer id1, final Integer id2);

    @Transactional
    @Query(value = "delete from Friendships where user_id1=:id1 and user_id2=:id2 returning *", nativeQuery = true)
    Optional<Friendship> deleteFriendship(final Integer id1, final Integer id2);
}
