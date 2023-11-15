package sirin.example.todolistbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sirin.example.todolistbackend.controller.auth.LoginUser;
import sirin.example.todolistbackend.entity.dto.auth.SessionUser;
import sirin.example.todolistbackend.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<SessionUser> getUser(@LoginUser SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok(sessionUser);
    }

}
