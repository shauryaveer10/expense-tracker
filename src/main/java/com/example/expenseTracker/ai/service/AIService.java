package com.example.expenseTracker.ai.service;

import com.example.expenseTracker.ai.client.GeminiClient;
import com.example.expenseTracker.entity.ExpenseCategory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AIService {
    private final GeminiClient geminiClient;

    public AIService(GeminiClient geminiClient){
        this.geminiClient = geminiClient;
    }

    public String getCategory(String description){
        return geminiClient.categorizeExpense(description);
    }
    public String generateSummary(BigDecimal total,
                                  ExpenseCategory topCategory,
                                  Map<ExpenseCategory, BigDecimal> categoryMap) {

        StringBuilder breakdown = new StringBuilder();

        for (Map.Entry<ExpenseCategory, BigDecimal> entry : categoryMap.entrySet()) {
            breakdown.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(", ");
        }

        String prompt = """
    You are a financial assistant.

    Based on the following expense data:
    Total Expense: %s
    Top Category: %s
    Category Breakdown: %s

    Give a short 1-2 line insight and suggestion.
    """.formatted(total, topCategory, breakdown.toString());

        return geminiClient.generateText(prompt);
    }
}
