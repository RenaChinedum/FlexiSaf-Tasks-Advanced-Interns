package com.rena.Todo.repository;

import com.rena.Todo.entity.Task;
import com.rena.Todo.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long taskID);

    List<Task> findAllByUser(User user, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.status = 0 AND t.user.id = :userID")
    List<Task> pendingTask(@Param("userID") Long id);
    @Query("SELECT t FROM Task t WHERE t.status = 1 AND t.user.id = :userID")
    List<Task> in_progressTask(@Param("userID") Long id);

    @Query("SELECT t FROM Task t WHERE t.status = 2 AND t.user.id = :userID")
    List<Task> completedTask(@Param("userID") Long id);

    @Query("SELECT t FROM Task t WHERE t.status = 3 AND t.user.id = :userID")
    List<Task> ignoredTask(@Param("userID") Long id);
}
