package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.dto.response.CustomerErrorDTO;
import br.com.mercadolivre.desafiospring.exceptions.InvalidBodyContentException;
import br.com.mercadolivre.desafiospring.models.Customer;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import br.com.mercadolivre.desafiospring.validators.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ApplicationRepository<Customer, Long> repo;

    public Customer findByID(Long id) throws IOException {
        Optional<Customer> optionalCustomer = repo.find(id);

        if(optionalCustomer.isEmpty()){
            return null;
        }

        return optionalCustomer.get();
    }

    public List<Customer> addClients(List<Customer> customers) throws IOException {
        List<CustomerErrorDTO> customerErrors = customers.stream().map(c -> {
            CustomerValidator validator = new CustomerValidator(c);


            return validator.validate();
        }).filter(Objects::nonNull).collect(Collectors.toList());


        if(!customerErrors.isEmpty()){
            throw new InvalidBodyContentException(customerErrors);
        }

        return repo.add(customers);
    }

    public List<Customer> listClients() throws IOException {
        return repo.read();
    }
}
