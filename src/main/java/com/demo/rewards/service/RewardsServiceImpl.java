package com.demo.rewards.service;

import com.demo.rewards.model.Transaction;
import com.demo.rewards.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the RewardsService interface,
 * providing logic to calculate rewards based on customer transactions.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RewardsServiceImpl implements RewardsService {

    private final TransactionRepository transactionRepository;

    @Override
    public int calculateRewards(double amount) {
        if (amount <= 50) {
            return 0; // No rewards for amounts $50 or below.
        } else if (amount <= 100) {
            return (int) (amount - 50); // 1 point for every dollar spent over $50.
        } else {
            // 1 point for dollars between 50-100 and 2 points for dollars over 100.
            return 50 + (int) (2 * (amount - 100));
        }
    }

    @Override
    public Map<YearMonth, Integer> calculateMonthlyRewards(String customerId, YearMonth yearMonth) {
        // Fetch transactions for the given customer and month.
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndTransactionDateBetween(
                customerId,
                yearMonth.atDay(1),
                yearMonth.atEndOfMonth());

        // Calculate rewards for each transaction and sum them up by month.
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> YearMonth.from(transaction.getTransactionDate()),
                        Collectors.summingInt(transaction -> calculateRewards(transaction.getAmount()))
                ));
    }
}
