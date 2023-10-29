package sirin.example.todolistbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sirin.example.todolistbackend.entity.dto.TodoListOnDayResponse;
import sirin.example.todolistbackend.entity.dto.TodoResponse;
import sirin.example.todolistbackend.entity.type.TodoTimeOfDay;
import sirin.example.todolistbackend.repository.TodoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoListOnDayResponse> getTodoListOnOneDay(LocalDate date) {
        Map<TodoTimeOfDay, List<TodoResponse>> todoMaps = todoRepository.findByStartDate(date).stream().map(TodoResponse::from)
            .collect(Collectors.groupingBy(TodoResponse::getTodoTimeOfDay));

        return todoMaps.entrySet().stream()
            .map(entry -> TodoListOnDayResponse.of(entry.getKey(), entry.getValue()))
            .toList();
    }

}
