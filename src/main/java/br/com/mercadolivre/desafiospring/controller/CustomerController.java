package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.request.CustomerDTO;
import br.com.mercadolivre.desafiospring.dto.response.CustomerCreatedDTO;
import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Customer;
import br.com.mercadolivre.desafiospring.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("customers")
    public ResponseEntity<List<CustomerDTO>> listCustomers(@RequestParam Map<String, Object> parameters) throws DataBaseReadException {
        List<Customer> customers = parameters.isEmpty() ? customerService.listClients() : customerService.findCustomerBy(parameters);
        return ResponseEntity.ok(CustomerDTO.modelToDTO(customers));
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Customer customer = customerService.findByID(id);
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(CustomerDTO.modelToDTO(customer));
    }

    @PostMapping("customers/")
    public ResponseEntity<List<CustomerCreatedDTO>> addCustomer(
            @RequestBody List<CustomerDTO> customersDtoToAdd, UriComponentsBuilder uriBuilder
    ) throws DataBaseReadException, DBEntryAlreadyExists, DataBaseWriteException {
        List<Customer> clientsToAdd = customersDtoToAdd.stream().map(CustomerDTO::dtoToModel).collect(Collectors.toList());
        List<Customer> addedCustomers = customerService.addClients(clientsToAdd);

        UriComponentsBuilder uriBuilderComponent = uriBuilder.path("/api/v1/customers/{id}");

        List<CustomerCreatedDTO> addedCustomersDTO = addedCustomers.stream().map(c -> {
            URI uri = uriBuilderComponent.build(c.getId());
            return CustomerCreatedDTO.modelToDTO(c, uri);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(addedCustomersDTO);
    }

}