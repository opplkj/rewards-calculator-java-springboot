package com.demo.rewards.repository;

import com.demo.rewards.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for accessing transaction data.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Finds transactions by customer ID and a date range.
     * This method is used to retrieve transactions for a specific customer within a given time period,
     * which is essential for calculating monthly rewards.
     *
     * @param customerId the ID of the customer whose transactions are being queried.
     * @param startDate the start date of the period for which transactions are being retrieved.
     * @param endDate the end date of the period for which transactions are being retrieved.
     * @return a list of transactions matching the criteria.
     */
    List<Transaction> findByCustomerIdAndTransactionDateBetween(String customerId, LocalDate startDate, LocalDate endDate);
}