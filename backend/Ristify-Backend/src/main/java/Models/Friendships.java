package Models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "friendships")
public class Friendships {
    @Id
    @Column(name = "userid1")
    @ManyToOne(targetEntity = Users.class)
    private Long userId1;

    @Id
    @Column(name = "userid2")
    @ManyToOne(targetEntity = Users.class)
    private Long userId2;

    @Column(nullable = false , name = "createdat")
    private Timestamp createdAt;

    public Friendships(){

    }

    public Friendships(final Long userid1, final Long userId2, final Timestamp createdAt) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.createdAt = createdAt;
    }

    public Long getUser1() {
        return userId1;
    }

    public Long getUser2() {
        return userId2;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setUser1(final Long user1) {
        this.userId1 = userId1;
    }

    public void setUser2(final Long user2) {
        this.userId2 = userId2;
    }

    public void setCreatedAt(final Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendships that = (Friendships) o;
        return Objects.equals(userId1, that.userId1) && Objects.equals(userId2, that.userId2) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId1, userId2, createdAt);
    }
}
