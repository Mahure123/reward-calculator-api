package com.test.reward_calculator_api.dto;

import java.util.List;

public class RewardResponseDto {

    private Long customerId;
    private String customerName;
    private String email;
    private Integer requestedMonths;
    private List<MonthlyRewardDto> monthlyRewards;
    private Integer totalRewardPoints;
    private List<TransactionDto> transactions;

    public RewardResponseDto() {
    }

    public RewardResponseDto(Long customerId,
                             String customerName,
                             String email,
                             Integer requestedMonths,
                             List<MonthlyRewardDto> monthlyRewards,
                             Integer totalRewardPoints,
                             List<TransactionDto> transactions) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.requestedMonths = requestedMonths;
        this.monthlyRewards = monthlyRewards;
        this.totalRewardPoints = totalRewardPoints;
        this.transactions = transactions;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRequestedMonths() {
        return requestedMonths;
    }

    public void setRequestedMonths(Integer requestedMonths) {
        this.requestedMonths = requestedMonths;
    }

    public List<MonthlyRewardDto> getMonthlyRewards() {
        return monthlyRewards;
    }

    public void setMonthlyRewards(List<MonthlyRewardDto> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }

    public Integer getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(Integer totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}