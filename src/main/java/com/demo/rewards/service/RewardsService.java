package com.demo.rewards.service;

import java.time.YearMonth;
import java.util.Map;

// Service interface for calculating customer rewards based on transaction history.
public interface RewardsService {

    /**
     * Calculates reward points for a single transaction amount.
     * This method follows the business rule:
     * - 2 points for every dollar spent over $100
     * - 1 point for every dollar spent over $50 (up to $100)
     *
     * @param amount the transaction amount for which rewards are to be calculated.
     * @return the total reward points earned for the transaction.
     */
    int calculateRewards(double amount);

    /**
     * Calculates the total reward points earned by a customer in a specific month.
     * This method aggregates all transactions for a customer within a given month
     * and calculates the total rewards based on each transaction's amount.
     *
     * @param customerId the ID of the customer whose rewards are being calculated.
     * @param yearMonth the month for which to aggregate transactions and calculate rewards.
     * @return a map where the key is the month (as YearMonth) and the value is the total rewards points for that month.
     */
    Map<YearMonth, Integer> calculateMonthlyRewards(String customerId, YearMonth yearMonth);
}

