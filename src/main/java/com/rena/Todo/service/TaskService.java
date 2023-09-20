package com.rena.Todo.service;

import com.rena.Todo.dto.request.CreateTaskRequest;
import com.rena.Todo.dto.request.UpdateTaskRequest;
import com.rena.Todo.dto.response.TaskResponse;
import com.rena.Todo.entity.Task;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(Long userID, CreateTaskRequest request);

    List<Task> getAllUserTask(Long id, Pageable pageable);

    Task getATask(Long id);

    TaskResponse updateTask(Long id, UpdateTaskRequest request);

    String deleteTask(Long id);

    String changeTaskStatus(Long id);

    void deactivateIgnoredTask(Long id);
}
