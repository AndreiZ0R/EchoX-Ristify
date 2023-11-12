package com.ristify.ristifybackend.models;

import com.ristify.ristifybackend.models.keys.FriendshipKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "friendships")
@IdClass(FriendshipKey.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Friendship {
    @Id
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id1", unique = true, nullable = false)
    private User userId1;

    @Id
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id2", unique = true, nullable = false)
    private User userId2;

    @Column(name = "created_at", nullable = false,columnDefinition = "timestamp DEFAULT NOW()", insertable = false)
    private Timestamp createdAt;
}