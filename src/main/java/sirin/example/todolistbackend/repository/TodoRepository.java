package sirin.example.todolistbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sirin.example.todolistbackend.entity.TodoEntity;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    @Query(value = ""
        + "SELECT   t FROM todos t "
        + "WHERE    :date BETWEEN t.startDate AND t.endDate "
        + "AND      t.userEntity.id = :userId"
    )
    List<TodoEntity> findByStartDateAndUserEntity_Id(LocalDate date, String userId);

    @Query(value = ""
        + "SELECT   t FROM todos t "
        + "WHERE    t.startDate <= :endDate AND t.endDate >= :startDate "
        + "AND      t.userEntity.id = :userId"
    )
    List<TodoEntity> findBetweenStartDateAndEndDate(
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate,
        @Param("userId") String userId);

}
