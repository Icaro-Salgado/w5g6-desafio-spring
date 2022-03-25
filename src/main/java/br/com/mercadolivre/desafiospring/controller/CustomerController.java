package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.request.CustomerDTO;
import br.com.mercadolivre.desafiospring.dto.response.CustomerCreatedDTO;
import br.com.mercadolivre.desafiospring.models.Customer;
import br.com.mercadolivre.desafiospring.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("customers/")
    public ResponseEntity<List<CustomerDTO>> listCustomers() throws IOException {
        List<Customer> customers = customerService.listClients();
        return ResponseEntity.ok(CustomerDTO.modelToDTO(customers));
    }

    @PostMapping("customers/")
    public ResponseEntity<List<CustomerCreatedDTO>> addCustomer(@RequestBody List<CustomerDTO> customersDtoToAdd, UriComponentsBuilder uriBuilder) throws IOException {
        List<Customer> clientsToAdd = customersDtoToAdd.stream().map(CustomerDTO::dtoToModel).collect(Collectors.toList());

        List<Customer> addedCustomers = customerService.addClients(clientsToAdd);

        List<CustomerCreatedDTO> addedCustomersDTO = addedCustomers.stream().map(c -> {
            URI uri = uriBuilder.path("/customers/{id}").buildAndExpand(c.getId()).toUri();
            return CustomerCreatedDTO.modelToDTO(c, uri);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(addedCustomersDTO);
    }
    @GetMapping("customers/find")
    public ResponseEntity<List<Customer>> findCustomerByUf(@RequestParam String uf) throws IOException, NoSuchMethodException {
        List<Customer> customers = customerService.findCustomerByUf(uf);
        return ResponseEntity.ok(customers);

    }}