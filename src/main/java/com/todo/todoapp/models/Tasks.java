package com.todo.todoapp.models;

import com.todo.todoapp.enums.Status;
import lombok.Data;

import javax.persistence.*;
import javax.sound.sampled.FloatControl;
import java.time.LocalDateTime;

import static com.todo.todoapp.enums.Status.*;

@Entity
@Data
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String Description;
    @ManyToOne
    Customers customer;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime UpdatedAt;
    private LocalDateTime completedAt;
}
