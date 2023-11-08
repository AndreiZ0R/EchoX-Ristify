package Models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "friendships")
public class Friendships {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Users user1;

    @ManyToOne
    private Users user2;

    @Column(nullable = false)
    private Timestamp createdAt;

    public Friendships(){

    }

    public Friendships(Long id, Users user1, Users user2, Timestamp createdAt) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.createdAt = createdAt;
    }

    //Getters

    public Long getId() {
        return id;
    }

    public Users getUser1() {
        return user1;
    }

    public Users getUser2() {
        return user2;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    //Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser1(Users user1) {
        this.user1 = user1;
    }

    public void setUser2(Users user2) {
        this.user2 = user2;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    //equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendships that = (Friendships) o;
        return Objects.equals(id, that.id) && Objects.equals(user1, that.user1) && Objects.equals(user2, that.user2) && Objects.equals(createdAt, that.createdAt);
    }

    //hashCode

    @Override
    public int hashCode() {
        return Objects.hash(id, user1, user2, createdAt);
    }
}
