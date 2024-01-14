package com.example.farm2.Unused;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income {
    private int incomeId;
    private int farmerId;
    private String incomeType;
    private BigDecimal amount;
    private LocalDate incomeDate;

    // Constructors (you can add more as needed)

    public Income() {
    }

    public Income(int farmerId, String incomeType, BigDecimal amount, LocalDate incomeDate) {
        this.farmerId = farmerId;
        this.incomeType = incomeType;
        this.amount = amount;
        this.incomeDate = incomeDate;
    }

    // Getters and Setters

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }
}