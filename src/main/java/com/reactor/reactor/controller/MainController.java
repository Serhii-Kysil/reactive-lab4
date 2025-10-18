package com.reactor.reactor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.reactor.reactor.entity.Message;
import com.reactor.reactor.service.MessageService;

@RestController
@RequestMapping("/controller")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MainController {

    private final MessageService messageService;

    @GetMapping
    public Flux<Message> list(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "3") Long count) {
        return messageService.list()
                .skip(start)
                .take(count);
    }

    @PostMapping
    public Mono<Message> create(@RequestBody Message message) {
        return messageService.addOne(message);
    }
}
