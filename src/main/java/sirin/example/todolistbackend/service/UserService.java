package sirin.example.todolistbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sirin.example.todolistbackend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

}
