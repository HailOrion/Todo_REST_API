package com.todo.todoapp.services;

import com.todo.todoapp.dtos.CustomerDto;
import com.todo.todoapp.dtos.TaskDto;
import com.todo.todoapp.dtos.TaskEditDto;
import com.todo.todoapp.dtos.TaskResponseDto;
import com.todo.todoapp.enums.Status;
import com.todo.todoapp.models.Customers;
import com.todo.todoapp.models.Tasks;

import java.util.List;

public interface CustomerServices {

    public void createUser(CustomerDto customerDto);
    public void createTask(TaskDto taskDto, Customers customers);
    public List<TaskResponseDto> viewAllTask();
    public TaskResponseDto viewOne(Long id);
    public List<TaskResponseDto> viewPending();
    public List<TaskResponseDto> viewDone();
    public List<TaskResponseDto> viewInProgress();
    public void editTask(Long id, TaskEditDto taskEditDto);
    public void deleteTask(Long id);
    public void MoveToPending(Long id);
    public void MoveToDone(Long id);

}
