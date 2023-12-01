package sirin.example.todolistbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sirin.example.todolistbackend.controller.auth.LoginUser;
import sirin.example.todolistbackend.entity.dto.TodoResponse;
import sirin.example.todolistbackend.entity.dto.auth.SessionUser;
import sirin.example.todolistbackend.service.CalendarService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    public List<TodoResponse> getTodoBetweenDate(
        @RequestParam(name = "startDate") LocalDate startDate,
        @RequestParam(name = "endDate") LocalDate endDate,
        @LoginUser SessionUser sessionUser
    ) {
        if (Objects.isNull(sessionUser)) {
            // TODO: 2023-11-19 add comment when throw UserNameNotFound
            throw new UsernameNotFoundException("");
        }
        return calendarService.getTodoBetweenDate(startDate, endDate, sessionUser.getId());
    }

}
