package sirin.example.todolistbackend.entity.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sirin.example.todolistbackend.entity.UserEntity;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserResponse {

    private String userId;

    private String nickName;

    private String imageUrl;

    public static UserResponse from(UserEntity userEntity) {
        return new UserResponse(
            userEntity.getId(),
            userEntity.getNickname(),
            userEntity.getImageUrl()
        );
    }

}
