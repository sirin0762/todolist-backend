package sirin.example.todolistbackend.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final String SESSION_LOGIN_KEY = "loginMember";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        HttpSession session = request.getSession();
//        session.setAttribute(SESSION_LOGIN_KEY, );
        response.sendRedirect("http://localhost:5173");
    }

}
