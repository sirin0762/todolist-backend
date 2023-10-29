package sirin.example.todolistbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sirin.example.todolistbackend.entity.dto.TodoListOnDayResponse;
import sirin.example.todolistbackend.service.TodoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoListOnDayResponse>> getTodoListOnOneDay(@RequestParam(name = "date") LocalDate date) {
        return ResponseEntity.ok(todoService.getTodoListOnOneDay(date));
    }

}
