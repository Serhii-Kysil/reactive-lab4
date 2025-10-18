package com.reactor.reactor.service;

import com.reactor.reactor.entity.Account;
import com.reactor.reactor.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Flux<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Mono<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Mono<Account> createAccount(Account account) {
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setIsBlocked(false);
        return accountRepository.save(account);
    }

    public Mono<Account> updateAccount(Long id, Account account) {
        return accountRepository.findById(id)
                .flatMap(existingAccount -> {
                    existingAccount.setAccountNumber(account.getAccountNumber());
                    existingAccount.setBalance(account.getBalance());
                    existingAccount.setCurrency(account.getCurrency());
                    existingAccount.setUpdatedAt(LocalDateTime.now());
                    return accountRepository.save(existingAccount);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Account not found with id: " + id)));
    }

    public Mono<Void> deleteAccount(Long id) {
        return accountRepository.deleteById(id);
    }

    public Mono<Account> blockAccount(Long id) {
        return accountRepository.findById(id)
                .flatMap(account -> {
                    account.setIsBlocked(true);
                    account.setUpdatedAt(LocalDateTime.now());
                    return accountRepository.save(account);
                });
    }

    public Mono<Account> unblockAccount(Long id) {
        return accountRepository.findById(id)
                .flatMap(account -> {
                    account.setIsBlocked(false);
                    account.setUpdatedAt(LocalDateTime.now());
                    return accountRepository.save(account);
                });
    }

    public Mono<Account> deposit(Long id, BigDecimal amount) {
        return accountRepository.findById(id)
                .flatMap(account -> {
                    if (account.getIsBlocked()) {
                        return Mono.error(new RuntimeException("Account is blocked"));
                    }
                    account.setBalance(account.getBalance().add(amount));
                    account.setUpdatedAt(LocalDateTime.now());
                    return accountRepository.save(account);
                });
    }

    public Mono<Account> makePayment(Long id, BigDecimal amount) {
        return accountRepository.findById(id)
                .flatMap(account -> {
                    if (account.getIsBlocked()) {
                        return Mono.error(new RuntimeException("Account is blocked"));
                    }
                    if (account.getBalance().compareTo(amount) < 0) {
                        return Mono.error(new RuntimeException("Insufficient funds"));
                    }
                    account.setBalance(account.getBalance().subtract(amount));
                    account.setUpdatedAt(LocalDateTime.now());
                    return accountRepository.save(account);
                });
    }

    public Mono<Account> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Flux<Account> getBlockedAccounts() {
        return accountRepository.findByIsBlocked(true);
    }
}
