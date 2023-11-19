package sirin.example.todolistbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sirin.example.todolistbackend.entity.TodoEntity;
import sirin.example.todolistbackend.entity.UserEntity;
import sirin.example.todolistbackend.entity.dto.TodoCreateRequest;
import sirin.example.todolistbackend.entity.dto.TodoListOnDayResponse;
import sirin.example.todolistbackend.entity.dto.TodoResponse;
import sirin.example.todolistbackend.entity.dto.auth.SessionUser;
import sirin.example.todolistbackend.entity.type.TodoTimeOfDay;
import sirin.example.todolistbackend.repository.TodoRepository;
import sirin.example.todolistbackend.repository.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TodoListOnDayResponse> getTodoListOnOneDay(LocalDate date, SessionUser sessionUser) {
        Map<TodoTimeOfDay, List<TodoResponse>> todoMaps = Arrays.stream(TodoTimeOfDay.values())
            .collect(Collectors.toMap(
                timeOfDay -> timeOfDay,
                timeOfDay -> todoRepository.findByStartDateAndUserEntity_Id(date, sessionUser.getId()).stream()
                    .map(TodoResponse::from)
                    .filter(todoResponse -> todoResponse.getTodoTimeOfDay() == timeOfDay)
                    .collect(Collectors.toList()),
                (list1, list2) -> list1,
                HashMap::new
            ));

        return todoMaps.entrySet().stream()
            .map(entry -> TodoListOnDayResponse.of(entry.getKey(), entry.getValue()))
            .sorted(Comparator.comparing(TodoListOnDayResponse::getTodoTimeOfDay))
            .toList();
    }

    @Transactional
    public Long createTodo(TodoCreateRequest request, SessionUser sessionUser) {
        UserEntity user = userRepository.findById(sessionUser.getId())
            .orElseThrow(() -> new UsernameNotFoundException(""));
        TodoEntity entity = request.toEntity(user);
        return todoRepository.save(entity).getId();
    }

    @Transactional
    public void updateTodo(Long id, TodoResponse requestTodo) {
        TodoEntity todo = todoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Todo입니다."));

        todo.update(requestTodo);
        todoRepository.save(todo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

}
