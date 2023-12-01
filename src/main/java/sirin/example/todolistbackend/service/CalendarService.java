package sirin.example.todolistbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sirin.example.todolistbackend.entity.dto.TodoResponse;
import sirin.example.todolistbackend.repository.TodoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final TodoRepository todoRepository;

    public List<TodoResponse> getTodoBetweenDate(LocalDate startDate, LocalDate endDate, String userId) {
        return todoRepository.findBetweenStartDateAndEndDate(startDate, endDate, userId)
            .stream().map(TodoResponse::from)
            .toList();
    }

}
