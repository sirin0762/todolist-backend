package sirin.example.todolistbackend.entity.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sirin.example.todolistbackend.entity.type.TodoTimeOfDay;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoListOnDayResponse {

    private TodoTimeOfDay todoTimeOfDay;

    private List<TodoResponse> todoResponses;

    public static TodoListOnDayResponse of(TodoTimeOfDay todoTimeOfDay, List<TodoResponse> todoResponses) {
        return new TodoListOnDayResponse(todoTimeOfDay, todoResponses);
    }

}
