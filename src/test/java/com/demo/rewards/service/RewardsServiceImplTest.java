package com.demo.rewards.service;

import com.demo.rewards.model.Transaction;
import com.demo.rewards.repository.TransactionRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RewardsServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardsServiceImpl rewardsService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateRewardsForAmounts() {
        assertEquals(rewardsService.calculateRewards(30), 0);
        assertEquals(rewardsService.calculateRewards(75), 25);
        assertEquals(rewardsService.calculateRewards(150), 150);
    }

    @Test
    public void testCalculateMonthlyRewards() {
        String customerId = "123";
        YearMonth yearMonth = YearMonth.of(2023, 4);
        List<Transaction> transactions = Arrays.asList(
                new Transaction(customerId, LocalDate.of(2023, 4, 5), 120),
                new Transaction(customerId, LocalDate.of(2023, 4, 15), 60)
        );

        when(transactionRepository.findByCustomerIdAndTransactionDateBetween(
                eq(customerId),
                any(LocalDate.class),
                any(LocalDate.class)
        )).thenReturn(transactions);

        Map<YearMonth, Integer> rewards = rewardsService.calculateMonthlyRewards(customerId, yearMonth);

        assertNotNull(rewards);
        assertTrue(rewards.containsKey(yearMonth));
        assertEquals(rewards.get(yearMonth).intValue(), 190);

        verify(transactionRepository, times(1))
                .findByCustomerIdAndTransactionDateBetween(eq(customerId), any(LocalDate.class), any(LocalDate.class));
    }
}
