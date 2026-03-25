package com.example.expenseTracker.dto;

import com.example.expenseTracker.entity.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ExpenseInsightsDTO {
    private BigDecimal totalExpense;
    private ExpenseCategory topCategory;
    private Map<ExpenseCategory ,BigDecimal> categoryBreakdown;
    private String summary;

}
