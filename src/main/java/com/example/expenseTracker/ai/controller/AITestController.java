package com.example.expenseTracker.ai.controller;

import com.example.expenseTracker.ai.client.GeminiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AITestController {

    private final GeminiClient geminiClient;

    public AITestController(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    @GetMapping("/api/ai/categorize")
    public String categorize(@RequestParam String description) {
        return geminiClient.categorizeExpense(description);
    }
}