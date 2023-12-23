package sirin.example.todolistbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sirin.example.todolistbackend.controller.auth.LoginUser;
import sirin.example.todolistbackend.entity.dto.TodoCreateRequest;
import sirin.example.todolistbackend.entity.dto.TodoListOnDayResponse;
import sirin.example.todolistbackend.entity.dto.TodoResponse;
import sirin.example.todolistbackend.entity.dto.auth.SessionUser;
import sirin.example.todolistbackend.service.TodoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoListOnDayResponse>> getTodoListOnOneDay(
        @RequestParam(name = "date") LocalDate date,
        @LoginUser SessionUser sessionUser
    ) {
        log.info("Request time: {}", LocalDate.now());
        return ResponseEntity.ok(todoService.getTodoListOnOneDay(date, sessionUser));
    }

    @PostMapping
    public ResponseEntity<String> createTodo(
        @RequestBody TodoCreateRequest request,
        @LoginUser SessionUser sessionUser
    ) {
        if (Objects.isNull(sessionUser)) {
            // TODO: 2023-11-19 add comment when throw UserNameNotFound
            throw new UsernameNotFoundException("");
        }
        Long id = todoService.createTodo(request, sessionUser);
        return ResponseEntity.ok(id.toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(
        @PathVariable(value = "id") Long id,
        @RequestBody TodoResponse todo
    ) {
        todoService.updateTodo(id, todo);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{id}")
    // join line : ctrl + shift + j
    public ResponseEntity<String> deleteTodo(@PathVariable(value = "id") Long id
    ) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("");
    }

}
