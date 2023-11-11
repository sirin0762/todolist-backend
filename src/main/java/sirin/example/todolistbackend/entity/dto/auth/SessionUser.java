package sirin.example.todolistbackend.entity.dto.auth;

import lombok.Getter;
import sirin.example.todolistbackend.entity.UserEntity;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String nickname;
    private String imageUrl;

    public SessionUser(UserEntity userEntity) {
        this.nickname = userEntity.getNickname();
        this.imageUrl = userEntity.getImageUrl();
    }

}
