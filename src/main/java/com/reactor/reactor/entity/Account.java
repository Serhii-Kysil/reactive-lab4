package com.reactor.reactor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("accounts")
public class Account {
    @Id
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private String currency;
    private Boolean isBlocked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
