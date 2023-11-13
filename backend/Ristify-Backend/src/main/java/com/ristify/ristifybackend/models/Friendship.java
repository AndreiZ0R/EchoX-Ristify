package com.ristify.ristifybackend.models;

import com.ristify.ristifybackend.models.keys.FriendshipKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Timestamp;

@Entity
@Table(name = "friendships")
@IdClass(FriendshipKey.class)
@Data
@NoArgsConstructor
public class Friendship {
    @Id
    @NonNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id1", unique = true, nullable = false)
    private User userId1;

    @Id
    @NonNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id2", unique = true, nullable = false)
    private User userId2;

    @NonNull
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp DEFAULT NOW()", insertable = false)
    private Timestamp createdAt;
}