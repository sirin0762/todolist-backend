package sirin.example.todolistbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sirin.example.todolistbackend.entity.TodoEntity;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findByStartDateAndUserEntity_Id(LocalDate startDate, String userId);

}
