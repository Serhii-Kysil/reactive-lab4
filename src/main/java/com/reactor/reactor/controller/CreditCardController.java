package com.reactor.reactor.controller;

import com.reactor.reactor.entity.CreditCard;
import com.reactor.reactor.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit-cards")
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;

    @GetMapping
    public Flux<CreditCard> getAllCreditCards() {
        return creditCardService.getAllCreditCards();
    }

    @GetMapping("/{id}")
    public Mono<CreditCard> getCreditCardById(@PathVariable Long id) {
        return creditCardService.getCreditCardById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CreditCard> createCreditCard(@RequestBody CreditCard creditCard) {
        return creditCardService.createCreditCard(creditCard);
    }

    @PutMapping("/{id}")
    public Mono<CreditCard> updateCreditCard(@PathVariable Long id, @RequestBody CreditCard creditCard) {
        return creditCardService.updateCreditCard(id, creditCard);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCreditCard(@PathVariable Long id) {
        return creditCardService.deleteCreditCard(id);
    }

    @GetMapping("/client/{clientId}")
    public Flux<CreditCard> getCreditCardsByClientId(@PathVariable Long clientId) {
        return creditCardService.getCreditCardsByClientId(clientId);
    }

    @GetMapping("/card-number/{cardNumber}")
    public Mono<CreditCard> getCreditCardByCardNumber(@PathVariable String cardNumber) {
        return creditCardService.getCreditCardByCardNumber(cardNumber);
    }

    @GetMapping("/account/{accountId}")
    public Flux<CreditCard> getCreditCardsByAccountId(@PathVariable Long accountId) {
        return creditCardService.getCreditCardsByAccountId(accountId);
    }
}
