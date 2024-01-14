package com.example.farm2.Unused;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expenses {
    private int expenseId;
    private int farmerId;
    private String expenseType;
    private BigDecimal amount;
    private LocalDate expenseDate;

    // Constructors (you can add more as needed)

    public Expenses() {
    }

    public Expenses(int farmerId, String expenseType, BigDecimal amount, LocalDate expenseDate) {
        this.farmerId = farmerId;
        this.expenseType = expenseType;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }

    // Getters and Setters

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }
}