package sirin.example.todolistbackend.entity.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sirin.example.todolistbackend.entity.TodoEntity;
import sirin.example.todolistbackend.entity.type.TodoTimeOfDay;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoResponse {

    private Long id;

    private String title;

    private String desc;

    private TodoTimeOfDay todoTimeOfDay;

    private boolean done;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    public static TodoResponse from(TodoEntity todoEntity) {
        return new TodoResponse(
            todoEntity.getId(),
            todoEntity.getTitle(),
            todoEntity.getDescription(),
            todoEntity.getTodoTimeOfDay(),
            todoEntity.isDone(),
            todoEntity.getStartDate(),
            todoEntity.getEndDate(),
            todoEntity.getStartTime(),
            todoEntity.getEndTime()
        );
    }

}
