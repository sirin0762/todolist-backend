package sirin.example.todolistbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
import sirin.example.todolistbackend.entity.dto.TodoCreateRequest;
import sirin.example.todolistbackend.entity.dto.TodoListOnDayResponse;
import sirin.example.todolistbackend.entity.dto.TodoResponse;
import sirin.example.todolistbackend.service.TodoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoListOnDayResponse>> getTodoListOnOneDay(@RequestParam(name = "date") LocalDate date) {
        return ResponseEntity.ok(todoService.getTodoListOnOneDay(date));
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody TodoCreateRequest request) {
        Long id = todoService.createTodo(request);
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
