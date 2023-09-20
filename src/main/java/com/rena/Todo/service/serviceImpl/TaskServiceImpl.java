package com.rena.Todo.service.serviceImpl;

import com.rena.Todo.dto.request.CreateTaskRequest;
import com.rena.Todo.dto.request.UpdateTaskRequest;
import com.rena.Todo.dto.response.TaskResponse;
import com.rena.Todo.entity.Task;
import com.rena.Todo.entity.User;
import com.rena.Todo.enums.Status;
import com.rena.Todo.repository.TaskRepository;
import com.rena.Todo.repository.UserRepository;
import com.rena.Todo.service.TaskService;
import com.rena.Todo.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;


    @Override
    public TaskResponse createTask(Long userID, CreateTaskRequest request){
        User user = userRepository.findById(userID).orElseThrow(()
                -> new RuntimeException("User Not Found!"));
        Task task = mapper.toTask(request);
        task.setStatus(Status.PENDING);
        task.setUser(user);
        taskRepository.save(task);
        return mapper.toResponse(task);
    }

    @Override
    public List<Task> getAllUserTask(Long id, Pageable pageable){
        User user = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User Not Found!"));
        pageable = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.asc("createdOn")));
        return taskRepository.findAllByUser(user, pageable);
    }

    @Override
    public Task getATask(Long id){
        return taskRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Task Not Found"));
    }

    @Override
    public TaskResponse updateTask(Long id, UpdateTaskRequest request){
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Task Not Found"));
        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            task.setContent(request.getContent());
        }
        if (request.getDeadLine() != null) {
            task.setDeadLine(request.getDeadLine());
        }
        task.setUpdatedOn(LocalDate.now());
        taskRepository.save(task);
        return mapper.toUpdateResponse(task);
    }

    @Override
    public String deleteTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Task Not Found"));
        taskRepository.delete(task);
        return "Task Deleted Successfully";
    }
    @Override
    public String changeTaskStatus(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Task Not Found"));
        if (task.getStatus().equals(Status.PENDING)) {
            task.setStatus(Status.IN_PROGRESS);
            taskRepository.save(task);
            return task.getTitle() + " " + "Started, Consistency is the key " +
                    "to finishing strong, Good Luck!";
        }
        if (task.getStatus().equals(Status.IN_PROGRESS)) {
            task.setStatus(Status.COMPLETED);
            taskRepository.save(task);
            return task.getTitle() + " " + "Completed " +
                    "Congratulations!";
        }
        if (task.getStatus().equals(Status.COMPLETED)) {
            return "You've Completed this Task, would you like to move it back?";
        }
        return "Invalid Task Status";
    }
    @Override
    public void deactivateIgnoredTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Task Not Found"));
        if(isDeadLineExceeded(task)){
            task.setStatus(Status.NOT_ATTENDED_TO);
            task.setActive(false);
            taskRepository.save(task);
        }
    }


    private boolean isDeadLineExceeded(Task task){
        LocalDate currentDate = LocalDate.now();
        LocalDate taskDeadline = task.getDeadLine();
        return currentDate.isAfter(taskDeadline);
    }



}
