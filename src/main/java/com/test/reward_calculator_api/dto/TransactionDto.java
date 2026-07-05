package com.test.reward_calculator_api.dto;

import java.time.LocalDate;

public class TransactionDto {

    private Long transactionId;
    private Double amount;
    private LocalDate transactionDate;
    private Integer rewardPoints;

    public TransactionDto() {
    }

    public TransactionDto(Long transactionId,
                          Double amount,
                          LocalDate transactionDate,
                          Integer rewardPoints) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.rewardPoints = rewardPoints;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}