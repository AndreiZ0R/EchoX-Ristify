package com.ristify.ristifybackend.repository;

import com.ristify.ristifybackend.models.Friendship;
import com.ristify.ristifybackend.models.keys.FriendshipKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, FriendshipKey> {

    @Query(value = "select * from Friendships f where f.user_id1=:userId or f.user_id2=:userId", nativeQuery = true)
    List<Friendship> getAllFriends(final Integer userId);
}
