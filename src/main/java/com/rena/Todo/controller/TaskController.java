package com.rena.Todo.controller;

import com.rena.Todo.dto.request.CreateTaskRequest;
import com.rena.Todo.dto.request.UpdateTaskRequest;
import com.rena.Todo.dto.response.TaskResponse;
import com.rena.Todo.entity.Task;
import com.rena.Todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;


    @PostMapping("/create/{id}")
    public ResponseEntity<TaskResponse> createTask(@PathVariable Long id,
                                                   @RequestBody CreateTaskRequest request){
        return new ResponseEntity<>(taskService.createTask(id,request), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getATask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getATask(id));
    }

    @GetMapping("/all-tasks/{id}")
    public ResponseEntity<List<Task>> allUserTask(@PathVariable Long id, Pageable pageable){
        return new ResponseEntity<>(taskService.getAllUserTask(id,pageable),HttpStatus.OK);
    }


    @PostMapping("/update-task/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request){
      return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @PostMapping("/push-status-forward/{id}")
    public ResponseEntity<String> pushStatusForward(@PathVariable Long id){
        return ResponseEntity.ok(taskService.changeTaskStatus(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @PostMapping("/deactivate/{id}")
    public void deactivateIgnoredTask(@PathVariable Long id){
        taskService.deactivateIgnoredTask(id);
    }


}
