package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Customer;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ApplicationRepository<Customer, Long> repo;

    public List<Customer> addClients(List<Customer> customers) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists {
        repo.add(customers);
        return customers;
    }

    public List<Customer> listClients() throws DataBaseReadException {
        return repo.read();
    }

    public List<Customer> findCustomerByUf(String uf) throws NoSuchMethodException, DataBaseReadException {
        return repo.findBy(Map.of("address.uf", uf));
    }
}
