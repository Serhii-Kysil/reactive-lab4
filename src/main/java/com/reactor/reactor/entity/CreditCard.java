package com.reactor.reactor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("credit_cards")
public class CreditCard {
    @Id
    private Long id;
    private Long clientId;
    private Long accountId;
    private String cardNumber;
    private String cardHolderName;
    private LocalDate expiryDate;
    private String cvv;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
