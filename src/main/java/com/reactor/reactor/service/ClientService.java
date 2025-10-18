package com.reactor.reactor.service;

import com.reactor.reactor.entity.Client;
import com.reactor.reactor.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Flux<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Mono<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Mono<Client> createClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        client.setUpdatedAt(LocalDateTime.now());
        return clientRepository.save(client);
    }

    public Mono<Client> updateClient(Long id, Client client) {
        return clientRepository.findById(id)
                .flatMap(existingClient -> {
                    existingClient.setFirstName(client.getFirstName());
                    existingClient.setLastName(client.getLastName());
                    existingClient.setEmail(client.getEmail());
                    existingClient.setPhoneNumber(client.getPhoneNumber());
                    existingClient.setUpdatedAt(LocalDateTime.now());
                    return clientRepository.save(existingClient);
                });
    }

    public Mono<Void> deleteClient(Long id) {
        return clientRepository.deleteById(id);
    }

    public Mono<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Flux<Client> getClientsByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }
}
