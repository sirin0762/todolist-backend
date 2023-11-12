package sirin.example.todolistbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sirin.example.todolistbackend.entity.type.Role;

import java.util.Objects;

@Entity
@Table(name = "users")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserEntity extends BaseEntity {

    @Id
    private String id;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String imageUrl;

    public static UserEntity of(String id, String username) {
        return new UserEntity(id, username, Role.USER,null);
    }

    public static UserEntity of(String id, String username, String imageUrl) {
        return new UserEntity(id, username, Role.USER,imageUrl);
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
