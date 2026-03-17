package com.example.expenseTracker.service;


import com.example.expenseTracker.dto.CreateExpenseRequestDTO;
import com.example.expenseTracker.dto.ExpenseResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    ExpenseResponseDTO createExpense(CreateExpenseRequestDTO requestDTO);
    List<ExpenseResponseDTO> getAllExpenses();
    void deleteExpense(Long id);
    List<ExpenseResponseDTO> getExpenseBetweenDates(LocalDate start , LocalDate end);
    BigDecimal getTotalExpenseBetweenDates(LocalDate start ,LocalDate end);
}
