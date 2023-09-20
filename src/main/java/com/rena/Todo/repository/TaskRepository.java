package com.rena.Todo.repository;

import com.rena.Todo.entity.Task;
import com.rena.Todo.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long taskID);

    List<Task> findAllByUser(User user, Pageable pageable);
}