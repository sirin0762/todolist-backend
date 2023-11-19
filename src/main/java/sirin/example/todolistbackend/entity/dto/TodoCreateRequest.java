package sirin.example.todolistbackend.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sirin.example.todolistbackend.entity.TodoEntity;
import sirin.example.todolistbackend.entity.UserEntity;
import sirin.example.todolistbackend.entity.type.TodoTimeOfDay;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class TodoCreateRequest {

    private String title;

    private String desc;

    private TodoTimeOfDay todoTimeOfDay;

    private boolean done;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public TodoEntity toEntity(UserEntity userEntity) {
        return new TodoEntity(
            this.getTitle(),
            this.getDesc(),
            this.getTodoTimeOfDay(),
            this.isDone(),
            this.getStartDate(),
            this.getEndDate(),
            userEntity
        );
    }

}
