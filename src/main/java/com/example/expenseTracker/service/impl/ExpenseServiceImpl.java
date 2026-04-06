package com.example.expenseTracker.service.impl;

import com.example.expenseTracker.ai.service.AIService;
import com.example.expenseTracker.dto.CreateExpenseRequestDTO;
import com.example.expenseTracker.dto.ExpenseInsightsDTO;
import com.example.expenseTracker.dto.ExpenseResponseDTO;
import com.example.expenseTracker.entity.Expense;
import com.example.expenseTracker.entity.ExpenseCategory;
import com.example.expenseTracker.repository.ExpenseRepository;
import com.example.expenseTracker.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final AIService aiService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, AIService aiService) {
        this.expenseRepository = expenseRepository;
        this.aiService = aiService;
    }


    private static final Map<String, ExpenseCategory> CATEGORY_MAP = Map.of(
            "FOOD", ExpenseCategory.FOOD,
            "TRAVEL", ExpenseCategory.TRAVEL,
            "SHOPPING", ExpenseCategory.SHOPPING,
            "BILLS", ExpenseCategory.BILLS
    );

    private ExpenseCategory mapToCategory(String aiCategory) {
        if (aiCategory == null) return ExpenseCategory.OTHER;

        return CATEGORY_MAP.getOrDefault(
                aiCategory.trim().toUpperCase(),
                ExpenseCategory.OTHER
        );
    }

    @Override
    public ExpenseResponseDTO createExpense(CreateExpenseRequestDTO requestDTO) {

        Expense ex = new Expense();
        ex.setTitle(requestDTO.getTitle());
        ex.setAmount(requestDTO.getAmount());
        ex.setDate(requestDTO.getDate());
        ex.setNote(requestDTO.getNote());

        String category = aiService.getCategory(requestDTO.getTitle());

        ExpenseCategory finalCategory;
        try {
            finalCategory = ExpenseCategory.valueOf(category.toUpperCase());
        } catch (Exception e) {
            finalCategory = ExpenseCategory.OTHER;
        }

        ex.setCategory(finalCategory);

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
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
        } else {
            throw new RuntimeException("id Doesn't exist");
        }
    }

    @Override
    public List<ExpenseResponseDTO> getExpenseBetweenDates(LocalDate start, LocalDate end) {
        List<Expense> expenseList = expenseRepository.findByDateBetween(start, end);
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
        List<Expense> expenseList = expenseRepository.findByDateBetween(start, end);
        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Expense temp : expenseList) {
            totalExpense = totalExpense.add(temp.getAmount());
        }

        return totalExpense;
    }

    @Override
    public ExpenseInsightsDTO getMonthlyInsights(int month, int year) {

        List<Expense> expenses = expenseRepository.findAll();

        Map<ExpenseCategory, BigDecimal> categoryMap = new HashMap<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Expense ex : expenses) {

            if (ex.getDate().getMonthValue() == month &&
                    ex.getDate().getYear() == year) {

                total = total.add(ex.getAmount());

                categoryMap.put(
                        ex.getCategory(),
                        categoryMap.getOrDefault(ex.getCategory(), BigDecimal.ZERO)
                                .add(ex.getAmount())
                );
            }
        }

        ExpenseCategory topCategory = null;
        BigDecimal max = BigDecimal.ZERO;

        for (Map.Entry<ExpenseCategory, BigDecimal> entry : categoryMap.entrySet()) {
            if (entry.getValue().compareTo(max) > 0) {
                max = entry.getValue();
                topCategory = entry.getKey();
            }
        }
        String summary = aiService.generateSummary(total, topCategory, categoryMap);

        return new ExpenseInsightsDTO(total, topCategory, categoryMap, summary);
    }
}