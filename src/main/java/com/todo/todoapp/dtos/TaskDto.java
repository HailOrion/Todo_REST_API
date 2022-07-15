package com.todo.todoapp.dtos;

import com.todo.todoapp.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    private String title;
    private String description;
    private Long customer_id;
    private Status status;
    private LocalDateTime createdAt;


}
