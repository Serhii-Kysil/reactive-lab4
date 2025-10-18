package com.reactor.reactor.repository;

import com.reactor.reactor.entity.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {
    Mono<Client> findByEmail(String email);
    Flux<Client> findByLastName(String lastName);
}
