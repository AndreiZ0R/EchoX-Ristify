package com.ristify.ristifybackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "friendships")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Friendship {
    @Id
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId1", unique = true, nullable = false)
    private Long userId1;

    @Id
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId2", unique = true, nullable = false)
    private Long userId2;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
}