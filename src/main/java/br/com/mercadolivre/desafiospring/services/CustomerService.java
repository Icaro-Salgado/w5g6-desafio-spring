package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.models.Customer;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ApplicationRepository<Customer, Long> repo;

    public List<Customer> addClients(List<Customer> customers) throws IOException {
        repo.add(customers);
        return customers;
    }

    public List<Customer> listClients() throws IOException {
        return repo.read();
    }

    public List<Customer> findCustomerByUf(String uf) throws IOException, NoSuchMethodException {
        return repo.findBy(Map.of( "address.uf", uf));
    }
}
