package com.example.expenseTracker.service.impl;

import com.example.expenseTracker.dto.CreateExpenseRequestDTO;
import com.example.expenseTracker.dto.ExpenseResponseDTO;
import com.example.expenseTracker.entity.Expense;
import com.example.expenseTracker.repository.ExpenseRepository;
import com.example.expenseTracker.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl (ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResponseDTO createExpense(CreateExpenseRequestDTO requestDTO) {
        Expense ex = new Expense();
        ex.setTitle(requestDTO.getTitle());
        ex.setAmount(requestDTO.getAmount());
        ex.setCategory(requestDTO.getCategory());
        ex.setDate(requestDTO.getDate());
        ex.setNote(requestDTO.getNote());

        Expense newEx = expenseRepository.save(ex);
        ExpenseResponseDTO res = new ExpenseResponseDTO();
        res.setId(newEx.getId());
        res.setTitle(newEx.getTitle());
        res.setCategory(newEx.getCategory());
        res.setAmount(newEx.getAmount());
        res.setDate(newEx.getDate());
        res.setNote(newEx.getNote());
        return res;
    }

    @Override
    public List<ExpenseResponseDTO> getAllExpenses() {
       List<Expense> allExpense = expenseRepository.findAll();
       List<ExpenseResponseDTO> allEx = new ArrayList<>();
        for (Expense temp : allExpense) {
            ExpenseResponseDTO response = new ExpenseResponseDTO();
            response.setId(temp.getId());
            response.setTitle(temp.getTitle());
            response.setAmount(temp.getAmount());
            response.setDate(temp.getDate());
            response.setCategory(temp.getCategory());
            response.setNote(temp.getNote());
            allEx.add(response);
        }
       return allEx;
    }

    @Override
    public void deleteExpense(Long id) {
    if(expenseRepository.existsById(id)){
        expenseRepository.deleteById(id);
    }
    else{
        throw new RuntimeException("id Doesn't exist");
    }
    }

    @Override
    public List<ExpenseResponseDTO> getExpenseBetweenDates(LocalDate start, LocalDate end) {
        List<Expense> expenseList = new ArrayList<>();
        expenseList = expenseRepository.findByDateBetween(start, end);
        List<ExpenseResponseDTO> expenseDtoList = new ArrayList<>();
        for (Expense temp : expenseList) {
            ExpenseResponseDTO response = new ExpenseResponseDTO();
            response.setId(temp.getId());
            response.setTitle(temp.getTitle());
            response.setAmount(temp.getAmount());
            response.setDate(temp.getDate());
            response.setCategory(temp.getCategory());
            response.setNote(temp.getNote());
            expenseDtoList.add(response);
        }
        return expenseDtoList;
    }

    @Override
    public BigDecimal getTotalExpenseBetweenDates(LocalDate start, LocalDate end) {
        List<Expense> expenseList = new ArrayList<>();
        expenseList = expenseRepository.findByDateBetween(start, end);
        BigDecimal totalExpense =BigDecimal.ZERO;
        for(Expense temp : expenseList){
            totalExpense.add(temp.getAmount());
        }
        return totalExpense;
    }
}
