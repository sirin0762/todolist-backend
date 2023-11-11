package sirin.example.todolistbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sirin.example.todolistbackend.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
