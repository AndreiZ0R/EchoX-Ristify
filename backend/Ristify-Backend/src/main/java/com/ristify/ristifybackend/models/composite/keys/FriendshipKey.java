package com.ristify.ristifybackend.models.composite.keys;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class FriendshipKey implements Serializable {
    private Long userId1;
    private Long userId2;

    public FriendshipKey(final Long userId1, final Long userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    public void setUserId1(final Long userId1) {
        this.userId1 = userId1;
    }

    public void setUserId2(final Long userId2) {
        this.userId2 = userId2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipKey that = (FriendshipKey) o;
        return Objects.equals(userId1, that.userId1) && Objects.equals(userId2, that.userId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId1, userId2);
    }
}
