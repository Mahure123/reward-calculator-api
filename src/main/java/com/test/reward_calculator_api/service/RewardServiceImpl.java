package com.test.reward_calculator_api.service;

import com.test.reward_calculator_api.dto.MonthlyRewardDto;
import com.test.reward_calculator_api.dto.RewardResponseDto;
import com.test.reward_calculator_api.dto.TransactionDto;
import com.test.reward_calculator_api.exception.CustomerNotFoundException;
import com.test.reward_calculator_api.model.Customer;
import com.test.reward_calculator_api.model.Transaction;
import com.test.reward_calculator_api.repository.TransactionRepository;
import com.test.reward_calculator_api.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceImpl.class);

	private final TransactionRepository transactionRepository;

	public RewardServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public RewardResponseDto calculateRewards(Long customerId, Integer months) {

		LOGGER.info("Calculating reward points for customerId : {}", customerId);

		Customer customer = getCustomer(customerId);

		List<Transaction> transactions = getCustomerTransactions(customerId, months);

		List<TransactionDto> transactionDtos = buildTransactionDtos(transactions);

		List<MonthlyRewardDto> monthlyRewardDtos = buildMonthlyRewards(transactions);

		Integer totalRewardPoints = calculateTotalRewardPoints(transactions);

		return buildRewardResponse(customer, months, monthlyRewardDtos, totalRewardPoints, transactionDtos);
	}

	private Customer getCustomer(Long customerId) {

		return transactionRepository.findCustomerById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException(customerId));
	}

	private List<Transaction> getCustomerTransactions(Long customerId, Integer months) {

		LocalDate fromDate = LocalDate.now().minusMonths(months);

		return transactionRepository.findTransactionsByCustomerId(customerId).stream()
				.filter(transaction -> !transaction.getTransactionDate().isBefore(fromDate)).sorted((transaction1,
						transaction2) -> transaction1.getTransactionDate().compareTo(transaction2.getTransactionDate()))
				.collect(Collectors.toList());

	}

	private int calculateRewardPoints(Double amount) {

		int rewardPoints = 0;

		if (amount > 100) {
			rewardPoints += (int) ((amount - 100) * 2);
			rewardPoints += 50;
		} else if (amount > 50) {
			rewardPoints += (int) (amount - 50);
		}

		return rewardPoints;
	}

	private List<TransactionDto> buildTransactionDtos(List<Transaction> transactions) {

		return transactions.stream()
				.map(transaction -> new TransactionDto(transaction.getTransactionId(), transaction.getAmount(),
						transaction.getTransactionDate(), calculateRewardPoints(transaction.getAmount())))
				.collect(Collectors.toList());
	}

	private Integer calculateTotalRewardPoints(List<Transaction> transactions) {

		return transactions.stream().mapToInt(transaction -> calculateRewardPoints(transaction.getAmount())).sum();
	}

	private List<MonthlyRewardDto> buildMonthlyRewards(List<Transaction> transactions) {

		return transactions.stream()

				.collect(Collectors.groupingBy(transaction -> transaction.getTransactionDate().getMonth()))

				.entrySet()

				.stream()

				.sorted(java.util.Map.Entry.comparingByKey())

				.map(entry -> new MonthlyRewardDto(

						entry.getKey().toString(),

						entry.getValue().size(),

						entry.getValue().stream()
								.mapToInt(transaction -> calculateRewardPoints(transaction.getAmount())).sum()

				))

				.collect(Collectors.toList());

	}

	private RewardResponseDto buildRewardResponse(Customer customer, Integer months,
			List<MonthlyRewardDto> monthlyRewardDtos, Integer totalRewardPoints, List<TransactionDto> transactionDtos) {

		return new RewardResponseDto(customer.getCustomerId(), customer.getCustomerName(), customer.getEmail(), months,
				monthlyRewardDtos, totalRewardPoints, transactionDtos);
	}

}