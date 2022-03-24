package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    // TODO: Trocar object por Service quando o model for criado
    private final ApplicationRepository<Object, Long> repo;

    public List<Object> addClients(List<Object> services) throws IOException {
        repo.add(services);

        return services;
    }
}
