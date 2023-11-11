package com.ristify.ristifybackend.models;

import com.ristify.ristifybackend.models.composite.keys.FriendshipKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "friendships")
@IdClass(FriendshipKey.class)
@NoArgsConstructor
@Getter
public class Friendship {
    @Id
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId1")
    private Long userId1;

    @Id
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId2")
    private Long userId2;

    @Column(nullable = false, name = "createdAt")
    private Timestamp createdAt;

    public Friendship(final Long userId1, final Long userId2, final Timestamp createdAt) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.createdAt = createdAt;
    }

    public void setUserId1(final Long userId1) {
        this.userId1 = userId1;
    }

    public void setUserId2(final Long userId2) {
        this.userId2 = userId2;
    }

    public void setCreatedAt(final Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(userId1, that.userId1) &&
                Objects.equals(userId2, that.userId2) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId1, userId2, createdAt);
    }

    @Override
    public String toString() {
        return "Friendship{" + "userId1=" + userId1 + ", userId2=" + userId2 + ", createdAt=" + createdAt + '}';
    }
}
