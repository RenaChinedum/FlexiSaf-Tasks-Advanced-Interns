package com.rena.Todo.utils;

import com.rena.Todo.dto.request.CreateTaskRequest;
import com.rena.Todo.dto.response.TaskResponse;
import com.rena.Todo.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@RequiredArgsConstructor
@Component
public class Mapper {


    public Task toTask(CreateTaskRequest request){
        return Task.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdOn(LocalDate.now())
                .isActive(true)
                .deadLine(request.getDeadLine())
                .build();
    }

    public TaskResponse toResponse(Task task){
        return TaskResponse.builder()
                .title(task.getTitle())
                .status(task.getStatus())
                .createdOn(task.getCreatedOn())
                .deadLine(task.getDeadLine())
                .isActive(task.isActive())
                .user(task.getUser().getFirstName() + " " + task.getUser().getLastName())
                .build();
    }
    public TaskResponse toUpdateResponse(Task task){
        return TaskResponse.builder()
                .title(task.getTitle())
                .status(task.getStatus())
                .createdOn(task.getCreatedOn())
                .updatedOn(task.getUpdatedOn())
                .deadLine(task.getDeadLine())
                .isActive(task.isActive())
                .user(task.getUser().getFirstName() + " " + task.getUser().getLastName())
                .build();
    }

}
