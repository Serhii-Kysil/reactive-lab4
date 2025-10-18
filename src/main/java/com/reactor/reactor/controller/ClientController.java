package com.reactor.reactor.controller;

import com.reactor.reactor.entity.Client;
import com.reactor.reactor.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public Flux<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Mono<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Client> createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public Mono<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteClient(@PathVariable Long id) {
        return clientService.deleteClient(id);
    }

    @GetMapping("/email/{email}")
    public Mono<Client> getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/lastName/{lastName}")
    public Flux<Client> getClientsByLastName(@PathVariable String lastName) {
        return clientService.getClientsByLastName(lastName);
    }
}
