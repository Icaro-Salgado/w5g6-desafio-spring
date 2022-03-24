package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.models.Client;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ApplicationRepository<Client, Long> repo;

    public List<Client> addClients(List<Client> clients) throws IOException {
        repo.add(clients);

        return clients;
    }
}
