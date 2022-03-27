package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.dto.response.AddressErrorDTO;
import br.com.mercadolivre.desafiospring.dto.response.CustomerErrorDTO;
import br.com.mercadolivre.desafiospring.dto.response.ErrorDTO;
import br.com.mercadolivre.desafiospring.exceptions.InvalidBodyContentException;
import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Customer;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import br.com.mercadolivre.desafiospring.validators.AddressValidator;
import br.com.mercadolivre.desafiospring.validators.CustomerValidator;
import br.com.mercadolivre.desafiospring.validators.ValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ApplicationRepository<Customer, Long> repo;


    public Customer findByID(Long id) {
        Optional<Customer> optionalCustomer = repo.find(id);

        if(optionalCustomer.isEmpty()){
            return null;
        }

        return optionalCustomer.get();
    }

    public List<Customer> addClients(List<Customer> customers) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists {

        List<ErrorDTO> customerErrors = customers.stream().map(c -> {
            ErrorDTO customerError = ValidatorService.validateObject(CustomerValidator.class, c, new CustomerErrorDTO());
            ErrorDTO addressError = ValidatorService.validateObject(AddressValidator.class, c.getAddress(), new AddressErrorDTO());

            if(addressError != null){
                if(customerError == null){
                    customerError = new CustomerErrorDTO().modelToDTO(c, new ArrayList<>());
                }
                customerError.pushMessage(addressError.getErrors());
            }
            return customerError;

        }).filter(Objects::nonNull).collect(Collectors.toList());


        if(!customerErrors.isEmpty()){
            throw new InvalidBodyContentException(customerErrors);
        }

        return repo.add(customers);
    }

    public List<Customer> listClients() throws DataBaseReadException {
        return repo.read();
    }

    public List<Customer> findCustomerByUf(String uf) throws NoSuchMethodException, DataBaseReadException {
        return repo.findBy(Map.of("address.uf", uf));
    }
}
