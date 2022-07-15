package com.todo.todoapp.dtos;

import com.todo.todoapp.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TaskResponseDto {
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
}
