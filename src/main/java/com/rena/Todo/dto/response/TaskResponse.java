package com.rena.Todo.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rena.Todo.entity.User;
import com.rena.Todo.enums.Status;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private String title;

    private Status status;

    private LocalDate createdOn;

    private LocalDate updatedOn;

    private boolean isActive;

    private LocalDate deadLine;

    private String user;
}
