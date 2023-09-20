package com.rena.Todo.dto.request;

import com.rena.Todo.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String firstName;

    private String lastName;

    private String email;

}
