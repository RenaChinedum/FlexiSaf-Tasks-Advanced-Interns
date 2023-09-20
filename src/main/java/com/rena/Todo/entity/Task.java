package com.rena.Todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rena.Todo.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tasks")
public class Task {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private String content;

    private Status status;

    private LocalDate createdOn;

    private LocalDate updatedOn;

    private boolean isActive;

    private LocalDate deadLine;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
