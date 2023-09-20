package com.rena.Todo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rena.Todo.entity.User;
import com.rena.Todo.enums.Status;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTaskRequest {
    private String title;

    private String content;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date format should be 'yyyy-MM-dd'")
    private LocalDate deadLine;

    private User user;


}
