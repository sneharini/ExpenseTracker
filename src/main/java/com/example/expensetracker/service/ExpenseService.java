package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //this annotation indicates that this class holds business logic
public class ExpenseService {


    @Autowired // dependency injection -- injecting expenseRepository
    private ExpenseRepository expenseRepository;


    // select * from expense
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // insert into expense(description, amount) values();
    public void saveExpense(Expense expense) {  // updating the expense
        expenseRepository.save(expense);
    }

    //select * from expense
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }
    // select * from expense
    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }
}
