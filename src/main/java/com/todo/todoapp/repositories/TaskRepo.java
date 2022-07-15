package com.todo.todoapp.repositories;

import com.todo.todoapp.enums.Status;
import com.todo.todoapp.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TaskRepo extends JpaRepository<Tasks,Long> {


    List<Tasks> findByStatus(Status status);
}
