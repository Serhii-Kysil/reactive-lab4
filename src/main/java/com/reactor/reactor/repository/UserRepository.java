package com.reactor.reactor.repository;

import com.reactor.reactor.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Query("SELECT id, username, password FROM users WHERE username = :username")
    Mono<User> findByUsername(String username);
}

