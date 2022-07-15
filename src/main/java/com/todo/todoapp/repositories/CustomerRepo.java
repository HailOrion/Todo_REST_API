package com.todo.todoapp.repositories;

import com.todo.todoapp.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customers,Long> {

    Optional<Customers> findByEmail(String email);
    Optional<Customers> findByEmailAndPassword(String email,String password);
}
