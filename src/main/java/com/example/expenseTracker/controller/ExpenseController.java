package com.example.expenseTracker.controller;

import com.example.expenseTracker.dto.CreateExpenseRequestDTO;
import com.example.expenseTracker.dto.ExpenseResponseDTO;
import com.example.expenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }
    @PostMapping
    public ExpenseResponseDTO createExpense(@Valid @RequestBody CreateExpenseRequestDTO request) {
        return expenseService.createExpense(request);
    }
    @GetMapping
    public List<ExpenseResponseDTO> getAllExpenses(){
        return expenseService.getAllExpenses();
    }
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
    @GetMapping("/date-range")
    public List<ExpenseResponseDTO> getExpensesBetweenDates(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        return expenseService.getExpenseBetweenDates(start, end);
    }

    @GetMapping("/total")
    public BigDecimal getTotalExpenseBetweenDates(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        return expenseService.getTotalExpenseBetweenDates(start, end);
    }


}
