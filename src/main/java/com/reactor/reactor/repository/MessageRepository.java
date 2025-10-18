package com.reactor.reactor.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.reactor.reactor.entity.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}

