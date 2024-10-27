package com.example.expensetracker.repository;


import com.example.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    //expense -- type of entity that the repository will manange
    // Long --   type of entity's primary key datatype



}
