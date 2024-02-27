package com.demo.rewards.controller;

import com.demo.rewards.service.RewardsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.Map;

/**
 * Handles requests related to rewards calculations,
 * providing endpoints to calculate and retrieve rewards information.
 */
@Slf4j
@RestController
@RequestMapping("/rewards") // Maps all requests with /rewards to this controller.
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RewardsController {

    private static final Logger logger = LoggerFactory.getLogger(RewardsController.class);
    private final RewardsService rewardsService;

    /**
     * Endpoint to calculate the total rewards earned by a customer for a specific month.
     *
     * @param customerId The ID of the customer whose rewards are being queried.
     * @param yearMonth  The month for which rewards are calculated, in the format yyyy-MM.
     * @return A ResponseEntity containing the monthly rewards for the customer.
     */

    // To test this below endpoint in postman or local here is the example endpoint:
    // http://localhost:8080/rewards/calculate/1?yearMonth=2024-01
    @GetMapping("/calculate/{customerId}")
    public ResponseEntity<?> calculateMonthlyRewards(
            @PathVariable String customerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) YearMonth yearMonth) {
        logger.info("Calculating rewards for customerId: {}, for month: {}", customerId, yearMonth);
        // Calls the service to calculate the rewards for the given customer and month.
        Map<YearMonth, Integer> rewards = rewardsService.calculateMonthlyRewards(customerId, yearMonth);

        if (rewards.isEmpty()) {
            logger.info("No rewards data available for customerId: {}, for month: {}", customerId, yearMonth);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rewards data available for the specified period.");
        }
        logger.info("Successfully calculated rewards for customerId: {}, for month: {}", customerId, yearMonth);
        // Returns the calculated rewards in the response entity.
        return ResponseEntity.ok(rewards);
    }
}
