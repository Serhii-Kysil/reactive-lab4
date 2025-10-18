package com.reactor.reactor.service;

import com.reactor.reactor.entity.CreditCard;
import com.reactor.reactor.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public Flux<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    public Mono<CreditCard> getCreditCardById(Long id) {
        return creditCardRepository.findById(id);
    }

    public Mono<CreditCard> createCreditCard(CreditCard creditCard) {
        creditCard.setCreatedAt(LocalDateTime.now());
        creditCard.setUpdatedAt(LocalDateTime.now());
        return creditCardRepository.save(creditCard);
    }

    public Mono<CreditCard> updateCreditCard(Long id, CreditCard creditCard) {
        return creditCardRepository.findById(id)
                .flatMap(existingCard -> {
                    existingCard.setClientId(creditCard.getClientId());
                    existingCard.setAccountId(creditCard.getAccountId());
                    existingCard.setCardNumber(creditCard.getCardNumber());
                    existingCard.setCardHolderName(creditCard.getCardHolderName());
                    existingCard.setExpiryDate(creditCard.getExpiryDate());
                    existingCard.setCvv(creditCard.getCvv());
                    existingCard.setUpdatedAt(LocalDateTime.now());
                    return creditCardRepository.save(existingCard);
                });
    }

    public Mono<Void> deleteCreditCard(Long id) {
        return creditCardRepository.deleteById(id);
    }

    public Flux<CreditCard> getCreditCardsByClientId(Long clientId) {
        return creditCardRepository.findByClientId(clientId);
    }

    public Mono<CreditCard> getCreditCardByCardNumber(String cardNumber) {
        return creditCardRepository.findByCardNumber(cardNumber);
    }

    public Flux<CreditCard> getCreditCardsByAccountId(Long accountId) {
        return creditCardRepository.findByAccountId(accountId);
    }
}
