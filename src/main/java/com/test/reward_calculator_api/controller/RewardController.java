package com.test.reward_calculator_api.controller;

import com.test.reward_calculator_api.dto.RewardResponseDto;
import com.test.reward_calculator_api.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/rewards")
@Validated
public class RewardController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(RewardController.class);

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{customerId}")
    public RewardResponseDto calculateRewards(

            @PathVariable
            @Min(value = 1, message = "Customer Id must be greater than zero")
            Long customerId,

            @RequestParam(defaultValue = "3")
            @Min(value = 1, message = "Months must be greater than zero")
            Integer months) {

        LOGGER.info("Reward request received for customerId : {} and months : {}",
                customerId,
                months);

        return rewardService.calculateRewards(customerId, months);
    }

}