package com.reactor.reactor.controller;

import com.reactor.reactor.entity.Account;
import com.reactor.reactor.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public Flux<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Mono<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/{id}")
    public Mono<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }

    @PostMapping("/{id}/block")
    public Mono<Account> blockAccount(@PathVariable Long id) {
        return accountService.blockAccount(id);
    }

    @PostMapping("/{id}/unblock")
    public Mono<Account> unblockAccount(@PathVariable Long id) {
        return accountService.unblockAccount(id);
    }

    @PostMapping("/{id}/deposit")
    public Mono<Account> deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/payment")
    public Mono<Account> makePayment(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return accountService.makePayment(id, amount);
    }

    @GetMapping("/account-number/{accountNumber}")
    public Mono<Account> getAccountByAccountNumber(@PathVariable String accountNumber) {
        return accountService.getAccountByAccountNumber(accountNumber);
    }

    @GetMapping("/blocked")
    public Flux<Account> getBlockedAccounts() {
        return accountService.getBlockedAccounts();
    }
}
