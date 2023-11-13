package sirin.example.todolistbackend.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import sirin.example.todolistbackend.entity.UserEntity;
import sirin.example.todolistbackend.entity.dto.auth.OAuthAttribute;
import sirin.example.todolistbackend.entity.dto.auth.SessionUser;
import sirin.example.todolistbackend.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String usernameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttribute attribute = OAuthAttribute.of(registrationId, usernameAttributeName, oAuth2User.getAttributes());

        UserEntity userEntity = saveOrUpdate(attribute);
        httpSession.setAttribute("user", new SessionUser(userEntity));

        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole().getKey())),
            attribute.getAttributes(),
            attribute.getNameAttributeKey()
        );
    }

    private UserEntity saveOrUpdate(OAuthAttribute attributes) {
        UserEntity user = userRepository.findById(attributes.getEmail())
            .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}
