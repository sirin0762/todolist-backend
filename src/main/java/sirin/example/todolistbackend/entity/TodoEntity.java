package sirin.example.todolistbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sirin.example.todolistbackend.entity.type.TodoTimeOfDay;

import java.time.LocalDate;
import java.time.LocalTime;

@Table
@Entity(name = "todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoEntity extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TodoTimeOfDay todoTimeOfDay;

    @Column(nullable = false)
    private boolean done;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

}
