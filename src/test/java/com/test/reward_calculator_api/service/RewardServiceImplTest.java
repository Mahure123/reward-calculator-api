
package com.test.reward_calculator_api.service;

import com.test.reward_calculator_api.dto.RewardResponseDto;
import com.test.reward_calculator_api.exception.CustomerNotFoundException;
import com.test.reward_calculator_api.model.Customer;
import com.test.reward_calculator_api.model.Transaction;
import com.test.reward_calculator_api.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RewardServiceImplTest {

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private RewardServiceImpl rewardService;

	private Customer customer;

	@BeforeEach
	void setUp() {

		customer = new Customer(101L, "John Smith", "john@gmail.com");
	}@Test
        void shouldThrowExceptionWhenCustomerNotFound() {

            when(transactionRepository.findCustomerById(999L))
                    .thenReturn(Optional.empty());

            assertThrows(

                    CustomerNotFoundException.class,

                    () -> rewardService.calculateRewards(999L, 3)

            );

        }@Test
        void shouldReturnZeroRewardWhenNoTransactions() {

            when(transactionRepository.findCustomerById(101L))
                    .thenReturn(Optional.of(customer));

            when(transactionRepository.findTransactionsByCustomerId(101L))
                    .thenReturn(Collections.emptyList());

            RewardResponseDto response =
                    rewardService.calculateRewards(101L, 3);

            assertEquals(

                    Integer.valueOf(0),

                    response.getTotalRewardPoints()

            );

            assertTrue(

                    response.getTransactions().isEmpty()

            );

        }@Test
        void shouldReturnZeroRewardForAmountBelowFifty() {

            when(transactionRepository.findCustomerById(101L))
                    .thenReturn(Optional.of(customer));

            when(transactionRepository.findTransactionsByCustomerId(101L))
                    .thenReturn(Collections.singletonList(

                            new Transaction(
                                    1001L,
                                    101L,
                                    40.0,
                                    LocalDate.now()
                            )

                    ));

            RewardResponseDto response =
                    rewardService.calculateRewards(101L, 3);

            assertEquals(

                    Integer.valueOf(0),

                    response.getTotalRewardPoints()

            );

        }@Test
        void shouldCalculateRewardBetweenFiftyAndHundred() {

            when(transactionRepository.findCustomerById(101L))
                    .thenReturn(Optional.of(customer));

            when(transactionRepository.findTransactionsByCustomerId(101L))
                    .thenReturn(Collections.singletonList(

                            new Transaction(
                                    1001L,
                                    101L,
                                    80.0,
                                    LocalDate.now()
                            )

                    ));

            RewardResponseDto response =
                    rewardService.calculateRewards(101L, 3);

            assertEquals(

                    Integer.valueOf(30),

                    response.getTotalRewardPoints()

            );

        }

	@Test
        void shouldCalculateRewardAboveHundred() {

            when(transactionRepository.findCustomerById(101L))
                    .thenReturn(Optional.of(customer));

            when(transactionRepository.findTransactionsByCustomerId(101L))
                    .thenReturn(Collections.singletonList(

                            new Transaction(
                                    1001L,
                                    101L,
                                    120.0,
                                    LocalDate.now()
                            )

                    ));

            RewardResponseDto response =
                    rewardService.calculateRewards(101L, 3);

            assertEquals(

                    Integer.valueOf(90),

                    response.getTotalRewardPoints()

            );

        }
}