package com.test.reward_calculator_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.reward_calculator_api.dto.RewardResponseDto;
import com.test.reward_calculator_api.exception.CustomerNotFoundException;
import com.test.reward_calculator_api.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RewardController.class)
class RewardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RewardService rewardService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldReturnRewardsSuccessfully() throws Exception {

		RewardResponseDto response = new RewardResponseDto(101L, "John Smith", "john@gmail.com", 3, emptyList(), 120,
				emptyList());

		when(rewardService.calculateRewards(101L, 3)).thenReturn(response);

		mockMvc.perform(get("/api/rewards/101").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
    void shouldReturnCustomerNotFound() throws Exception {

        when(rewardService.calculateRewards(999L, 3))
                .thenThrow(new CustomerNotFoundException(999L));

        mockMvc.perform(get("/api/rewards/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

	@Test
	void shouldReturnBadRequestForInvalidCustomerId() throws Exception {

		mockMvc.perform(get("/api/rewards/0").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

	@Test
	void shouldReturnBadRequestForInvalidMonths() throws Exception {

		mockMvc.perform(get("/api/rewards/101").param("months", "-1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

}