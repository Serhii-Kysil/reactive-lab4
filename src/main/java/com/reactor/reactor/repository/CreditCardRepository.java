package com.reactor.reactor.repository;

import com.reactor.reactor.entity.CreditCard;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CreditCardRepository extends ReactiveCrudRepository<CreditCard, Long> {
    Flux<CreditCard> findByClientId(Long clientId);
    Mono<CreditCard> findByCardNumber(String cardNumber);
    Flux<CreditCard> findByAccountId(Long accountId);
}
