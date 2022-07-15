package com.todo.todoapp.services;

import com.todo.todoapp.dtos.CustomerDto;
import com.todo.todoapp.dtos.TaskDto;
import com.todo.todoapp.dtos.TaskEditDto;
import com.todo.todoapp.dtos.TaskResponseDto;
import com.todo.todoapp.enums.Status;
import com.todo.todoapp.exceptions.CustomAppException;
import com.todo.todoapp.models.Customers;
import com.todo.todoapp.models.Tasks;
import com.todo.todoapp.repositories.CustomerRepo;
import com.todo.todoapp.repositories.TaskRepo;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImple implements CustomerServices{

    final CustomerRepo customerRepo;
    final TaskRepo taskRepo;

    final Status status;

    public CustomerServiceImple(CustomerRepo customerRepo, TaskRepo taskRepo, Status status) {
        this.customerRepo = customerRepo;
        this.taskRepo = taskRepo;
        this.status = status;
    }


    @Override
    public void createUser(CustomerDto customerDto) {
        Customers customers=new Customers();
        Optional customerInDb=customerRepo.findByEmail(customerDto.getEmail());
        if(customerInDb.isPresent()){
            throw new CustomAppException( "Customer's email "+customerDto.getEmail()+" has been used", HttpStatus.CONFLICT);}

        customers.setEmail(customerDto.getEmail());
        customers.setFullName(customerDto.getFullName());
        customers.setPassword(customerDto.getPassword());
        customerRepo.save(customers);
    }

    @Override
    public void createTask(TaskDto taskDto,Customers customers) {
        Tasks task=new Tasks();
        Customers customer=getCustomerLoggedIn(customers.getId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCustomer(customer);
        task.setStatus(status.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        taskRepo.save(task);
    }

    @Override
    public List<TaskResponseDto> viewAllTask() {
        List<TaskResponseDto> all=new ArrayList<>();
            List<Tasks>inDb=taskRepo.findAll();
            for(Tasks i:inDb){
                TaskResponseDto task=new TaskResponseDto();
                task.setTitle(i.getTitle());
                task.setStatus(i.getStatus());
                task.setDescription(i.getDescription());
                task.setCreatedAt(i.getCreatedAt());
                task.setUpdatedAt(i.getUpdatedAt());
                task.setUpdatedAt(i.getUpdatedAt());
                all.add(task);
            }
        return all;
    }

    @Override
    public TaskResponseDto viewOne(Long id) {
            Tasks one=taskRepo.findById(id).orElseThrow(()->new CustomAppException("Task not found", HttpStatus.NOT_FOUND));
            TaskResponseDto task=new TaskResponseDto();
            task.setTitle(one.getTitle());
            task.setStatus(one.getStatus());
            task.setDescription(one.getDescription());
            task.setCreatedAt(one.getCreatedAt());
            task.setUpdatedAt(one.getUpdatedAt());
            task.setUpdatedAt(one.getUpdatedAt());
        return task;
    }

    @Override
    public List<TaskResponseDto> viewPending() {

        List<TaskResponseDto> all=new ArrayList<>();
        List<Tasks>inDb=taskRepo.findByStatus(status.PENDING);
        for(Tasks i:inDb){
            TaskResponseDto task=new TaskResponseDto();
            task.setTitle(i.getTitle());
            task.setStatus(i.getStatus());
            task.setDescription(i.getDescription());
            task.setCreatedAt(i.getCreatedAt());
            task.setUpdatedAt(i.getUpdatedAt());
            task.setUpdatedAt(i.getUpdatedAt());
            all.add(task);
        }
        return all;
    }

    @Override
    public List<TaskResponseDto> viewDone() {
        List<TaskResponseDto> all=new ArrayList<>();
        List<Tasks>inDb=taskRepo.findByStatus(status.DONE);
        for(Tasks i:inDb){
            TaskResponseDto task=new TaskResponseDto();
            task.setTitle(i.getTitle());
            task.setStatus(i.getStatus());
            task.setDescription(i.getDescription());
            task.setCreatedAt(i.getCreatedAt());
            task.setUpdatedAt(i.getUpdatedAt());
            task.setUpdatedAt(i.getUpdatedAt());
            all.add(task);
        }
        return all;
    }

    @Override
    public List<TaskResponseDto> viewInProgress() {
        List<TaskResponseDto> all=new ArrayList<>();
        List<Tasks>inDb=taskRepo.findByStatus(status.IN_PROGRESS);
        for(Tasks i:inDb){
            TaskResponseDto task=new TaskResponseDto();
            task.setTitle(i.getTitle());
            task.setStatus(i.getStatus());
            task.setDescription(i.getDescription());
            task.setCreatedAt(i.getCreatedAt());
            task.setUpdatedAt(i.getUpdatedAt());
            task.setUpdatedAt(i.getUpdatedAt());
            all.add(task);
        }
        return all;
    }

    @Override
    public void editTask(Long id,TaskEditDto taskEditDto) {
        Tasks taskIndb=taskRepo.findById(id).orElseThrow(()->new CustomAppException("Task not found", HttpStatus.NOT_FOUND));

        taskIndb.setTitle(taskEditDto.getTitle());
        taskIndb.setDescription(taskEditDto.getDescription());
        taskIndb.setUpdatedAt(LocalDateTime.now());
        taskRepo.save(taskIndb);

    }

    @Override
    public void deleteTask(Long id) {
        Tasks taskIndb=taskRepo.findById(id).orElseThrow(()->new CustomAppException("Task not found", HttpStatus.NOT_FOUND));
        taskRepo.delete(taskIndb);
    }

    @Override
    public void MoveToPending(Long id) {
        Tasks taskIndb=taskRepo.findById(id).orElseThrow(()->new CustomAppException("Task not found", HttpStatus.NOT_FOUND));
        taskIndb.setStatus(status.PENDING);
        taskIndb.setUpdatedAt(LocalDateTime.now());
        taskRepo.save(taskIndb);
    }

    @Override
    public void MoveToDone(Long id) {
        Tasks taskIndb=taskRepo.findById(id).orElseThrow(()->new CustomAppException("Task not found", HttpStatus.NOT_FOUND));
        taskIndb.setStatus(status.DONE);
        taskIndb.setCompletedAt(LocalDateTime.now());
        taskRepo.save(taskIndb);
    }

    private Customers getCustomerLoggedIn(Long id){
        return customerRepo.findById(id).orElseThrow(()-> new CustomAppException("Customer not found",HttpStatus.NOT_FOUND));
    }
}
