package com.demo.rewards.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a customer transaction, capturing the essential information needed
 * for rewards calculation, such as the transaction amount and date.
 */
@Entity // Marks this class as a JPA entity.
@Table(name = "transactions") // Specifies the table name in the database.
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor // Lombok annotation to generate a no-args constructor.
@AllArgsConstructor // Lombok annotation to generate an all-args constructor.
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L; // Ensures compatibility during serialization and deserialization.

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way ID is generated.
    private Long id; // Unique identifier for the transaction.

    @Column(name = "customer_id", nullable = false) // Maps this field to a column in the table.
    private String customerId; // Identifier of the customer who made the transaction.

    @Column(name = "amount", nullable = false)
    private Double amount; // The monetary value of the transaction.

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate; // The date when the transaction occurred.

    public Transaction(String customerId,LocalDate transactionDate, int amount) {
        this.customerId = customerId;
        this.amount = (double) amount;
        this.transactionDate = transactionDate;
    }



// Note: No need for explicit getters, setters, toString, etc., due to @Data
}
