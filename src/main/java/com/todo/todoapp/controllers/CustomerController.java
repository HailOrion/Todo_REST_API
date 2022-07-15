package com.todo.todoapp.controllers;


import com.todo.todoapp.dtos.*;
import com.todo.todoapp.enums.Status;
import com.todo.todoapp.exceptions.CustomAppException;
import com.todo.todoapp.models.Customers;
import com.todo.todoapp.repositories.CustomerRepo;
import com.todo.todoapp.repositories.TaskRepo;
import com.todo.todoapp.services.CustomerServiceImple;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    TaskRepo taskRepo;

    Status status;


    @RequestMapping("/login")
    public ResponseEntity<String>login(@RequestBody CustomerSignInDto signInDto){

       Customers customerInDb=customerRepo.findByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword()).orElseThrow(()->new CustomAppException("Email or password is incorrect", HttpStatus.NOT_ACCEPTABLE));

        return new ResponseEntity<>("User logged in",HttpStatus.OK);
    }

    @RequestMapping("/signup")
    public ResponseEntity<String>signup(@RequestBody CustomerDto customerDto){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        //CustomerDto customerDto=new CustomerDto();
        serviceImple.createUser(customerDto);
        return new ResponseEntity<>("Registration Successful",HttpStatus.OK);
    }


    @RequestMapping("/{customer}/create_task")
    public ResponseEntity<String>createTask(@RequestBody TaskDto taskDto,@PathVariable Customers customer){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        serviceImple.createTask(taskDto,customer);
        return new ResponseEntity<>("Task created successfully",HttpStatus.OK);
    }

    @RequestMapping("/view_all")
    public List<TaskResponseDto>viewAll(){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        return serviceImple.viewAllTask();
    }

    @RequestMapping("/view_one/{id}")
    public TaskResponseDto viewOne( @PathVariable Long id){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        return serviceImple.viewOne(id);
    }
    @RequestMapping("/view_pending")
    public List<TaskResponseDto> viewPending(){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        return serviceImple.viewPending();
    }
    @RequestMapping("/view_done")
    public List<TaskResponseDto> done(){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        return serviceImple.viewDone();
    }
    @RequestMapping("/view_in_progress")
    public List<TaskResponseDto> viewInProgress(){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        return serviceImple.viewInProgress();
    }
    @RequestMapping("/edit_task/{id}")
    public ResponseEntity<String> editTask(@PathVariable Long id, @RequestBody TaskEditDto taskEditDto){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        serviceImple.editTask(id,taskEditDto);
        return new ResponseEntity<>("Task Edited",HttpStatus.OK);
    }
    @RequestMapping("/move_to_pending/{id}")
    public ResponseEntity<String> moveToPending( @PathVariable Long id){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        serviceImple.MoveToPending(id);
        return new ResponseEntity<>("Task moved to pending",HttpStatus.OK);
    }
    @RequestMapping("/move_to_done/{id}")
    public ResponseEntity<String> moveToDone(@PathVariable Long id){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        serviceImple.MoveToDone(id);
        return new ResponseEntity<>("Task marked as done",HttpStatus.OK);
    }
    @RequestMapping("/delete_task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        CustomerServiceImple serviceImple=new CustomerServiceImple(customerRepo,taskRepo,status);
        serviceImple.deleteTask(id);
        return new ResponseEntity<>("Task deleted",HttpStatus.OK);
    }

}

