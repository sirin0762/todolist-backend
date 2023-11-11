package sirin.example.todolistbackend.entity.dto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sirin.example.todolistbackend.entity.UserEntity;

import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthAttribute {

    private Map<String, Object> attributes;

    private String nameAttributeKey;

    private String id;

    private String username;

    public static OAuthAttribute of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttribute ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return new OAuthAttribute(attributes,
            userNameAttributeName,
            (String) attributes.get("id"),
            (String) attributes.get("username"));
    }

    public UserEntity toEntity() {
        return UserEntity.of(id, username);
    }

}
