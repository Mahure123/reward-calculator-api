package com.test.reward_calculator_api.repository;

import com.test.reward_calculator_api.model.Customer;
import com.test.reward_calculator_api.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

	private final List<Customer> customers = new ArrayList<>();
	private final List<Transaction> transactions = new ArrayList<>();

	public TransactionRepository() {
		loadCustomers();
		loadTransactions();
	}

	private void loadCustomers() {

		customers.add(new Customer(101L, "John Smith", "john@gmail.com"));
		customers.add(new Customer(102L, "Alice Brown", "alice@gmail.com"));
		customers.add(new Customer(103L, "David Miller", "david@gmail.com"));

	}

	private void loadTransactions() {

	    LocalDate today = LocalDate.now();

	    // Customer 101
	    transactions.add(new Transaction(1001L, 101L, 120.00, today.minusMonths(2).withDayOfMonth(10)));
	    transactions.add(new Transaction(1002L, 101L, 75.00, today.minusMonths(2).withDayOfMonth(20)));
	    transactions.add(new Transaction(1003L, 101L, 150.00, today.minusMonths(1).withDayOfMonth(15)));
	    transactions.add(new Transaction(1004L, 101L, 90.00, today.withDayOfMonth(5)));

	    // Customer 102
	    transactions.add(new Transaction(1005L, 102L, 130.00, today.minusMonths(2).withDayOfMonth(18)));
	    transactions.add(new Transaction(1006L, 102L, 45.00, today.minusMonths(1).withDayOfMonth(25)));
	    transactions.add(new Transaction(1007L, 102L, 220.00, today.withDayOfMonth(8)));

	    // Customer 103
	    transactions.add(new Transaction(1008L, 103L, 60.00, today.minusMonths(2).withDayOfMonth(12)));
	    transactions.add(new Transaction(1009L, 103L, 105.00, today.minusMonths(1).withDayOfMonth(2)));
	    transactions.add(new Transaction(1010L, 103L, 250.00, today.withDayOfMonth(18)));
	}

	public Optional<Customer> findCustomerById(Long customerId) {

		return customers.stream().filter(customer -> customer.getCustomerId().equals(customerId)).findFirst();
	}

	public List<Transaction> findTransactionsByCustomerId(Long customerId) {

		return transactions.stream().filter(transaction -> transaction.getCustomerId().equals(customerId))
				.collect(Collectors.toList());
	}

}