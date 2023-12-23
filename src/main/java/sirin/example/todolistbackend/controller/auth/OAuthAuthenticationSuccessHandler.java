package sirin.example.todolistbackend.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import sirin.example.todolistbackend.entity.dto.auth.SessionUser;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final HttpSession httpSession;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        String redirectUri = createRedirectUri(user);
        response.sendRedirect(redirectUri);
    }

    private String createRedirectUri(SessionUser user) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
//            .scheme("http")
//            .host("sirin-todolist.s3-website.ap-northeast-2.amazonaws.com")
//            .scheme("https")
//            .host("todolist-frontend-sirin0762.vercel.app")
            .scheme("http")
            .host("localhost")
            .port(5173)
            .path("/auth/redirect")
            .queryParam("nickname", user.getNickname())
            .queryParam("imageUrl", user.getImageUrl())
            .build()
            .encode();

        return uriComponents.toString();
    }

}
