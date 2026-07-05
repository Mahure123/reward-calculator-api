package com.test.reward_calculator_api.dto;

public class MonthlyRewardDto {

    private String month;
    private Integer totalTransactions;
    private Integer rewardPoints;

    public MonthlyRewardDto() {
    }

    public MonthlyRewardDto(String month,
                            Integer totalTransactions,
                            Integer rewardPoints) {
        this.month = month;
        this.totalTransactions = totalTransactions;
        this.rewardPoints = rewardPoints;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Integer totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}