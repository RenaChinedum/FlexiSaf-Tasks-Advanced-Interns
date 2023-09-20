package com.rena.Todo.dto.request;

import com.rena.Todo.enums.Status;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
    private String title;

    private String content;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date format should be 'yyyy-MM-dd'")
    private LocalDate deadLine;

}
