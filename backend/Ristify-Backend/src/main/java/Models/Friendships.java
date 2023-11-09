package Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "friendships")
@NoArgsConstructor
@Getter
public class Friendships {
    @Id
    @Column(name = "userId1")
    @ManyToOne(targetEntity = Users.class)
    private Long userId1;
    @Id
    @Column(name = "userId2")
    @ManyToOne(targetEntity = Users.class)
    private Long userId2;
    @Column(nullable = false, name = "createdAt")
    private Timestamp createdAt;

    public Friendships(final Long userid1, final Long userId2, final Timestamp createdAt) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.createdAt = createdAt;
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
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendships that = (Friendships) o;
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
        return "Friendships{" + "userId1=" + userId1 + ", userId2=" + userId2 + ", createdAt=" + createdAt + '}';
    }
}
