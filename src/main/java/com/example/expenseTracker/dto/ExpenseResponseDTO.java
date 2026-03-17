package com.example.expenseTracker.dto;

import com.example.expenseTracker.entity.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseResponseDTO {

    private Long id;

    private String title;

    private BigDecimal amount;


    private ExpenseCategory category;

    private LocalDate date;
    private String note;
}
