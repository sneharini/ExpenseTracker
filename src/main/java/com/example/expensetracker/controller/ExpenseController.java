package com.example.expensetracker.controller;

import org.springframework.ui.Model;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Expense> expenses = expenseService.getAllExpenses();
        BigDecimal totalAmount = expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("expenses", expenses);
        model.addAttribute("totalAmount", totalAmount);
        return "index";
    }

    @GetMapping("/addExpense")
    public String showAddExpense(Model model) {
        Expense expense = new Expense();
        model.addAttribute("expense", expense);
        return "add-expense";
    }

    @PostMapping("/saveExpense")
    public String saveExpense(@ModelAttribute("expense") Expense expense, Model model) {
        expenseService.saveExpense(expense);
        return "redirect:/";
    }

    @GetMapping("editExpense/{id}")
    public String showUpdateExpense(Model model, @PathVariable long id) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "update-expense";
    }

    @PostMapping("/updateExpense/{id}")
    public String updateExpense(@ModelAttribute("expense") Expense expense , @PathVariable("id") long id) {
        Expense exisitingExpense = expenseService.getExpenseById(id);
        exisitingExpense.setDescription(expense.getDescription());
        exisitingExpense.setAmount(expense.getAmount());
        expenseService.saveExpense(exisitingExpense);
        return "redirect:/";
    }

    @GetMapping("/deleteExpense/{id}")
    public String deleteExpense(@PathVariable long id) {
        expenseService.deleteExpenseById(id);
        return "redirect:/";
    }




}
