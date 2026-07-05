package com.test.reward_calculator_api.model;

import java.time.LocalDate;

public class Transaction {

    private Long transactionId;
    private Long customerId;
    private Double amount;
    private LocalDate transactionDate;

    public Transaction() {
    }

    public Transaction(Long transactionId, Long customerId, Double amount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}