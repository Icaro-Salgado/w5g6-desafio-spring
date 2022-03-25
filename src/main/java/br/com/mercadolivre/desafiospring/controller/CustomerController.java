package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.request.CustomerDTO;
import br.com.mercadolivre.desafiospring.models.Customer;
import br.com.mercadolivre.desafiospring.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("customers/")
    public ResponseEntity<List<Customer>> listCustomers() throws IOException {
        return ResponseEntity.ok(customerService.listClients());
    }

    @PostMapping("customers/")
    public ResponseEntity<List<CustomerDTO>> addCustomer(@RequestBody List<CustomerDTO> customersDtoToAdd) throws IOException {
        List<Customer> clientsToAdd = customersDtoToAdd.stream().map(CustomerDTO::dtoToModel).collect(Collectors.toList());

        List<Customer> addedCustomers = customerService.addClients(clientsToAdd);
        List<CustomerDTO> addedCustomersDTO = addedCustomers.stream().map(CustomerDTO::modelToDTO).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(addedCustomersDTO);
    }
}
