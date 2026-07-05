package com.test.reward_calculator_api.service;

import com.test.reward_calculator_api.dto.RewardResponseDto;

public interface RewardService {

    /**
     * Calculates reward points for a customer
     * within the requested time period.
     *
     * @param customerId Customer Id
     * @param months Number of months to consider
     * @return RewardResponseDto
     */
    RewardResponseDto calculateRewards(Long customerId, Integer months);

}