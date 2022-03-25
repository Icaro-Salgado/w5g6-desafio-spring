package br.com.mercadolivre.desafiospring.dto.request;

import br.com.mercadolivre.desafiospring.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String email;
    private AddressDTO address;

    public Customer dtoToModel(){
        return new Customer(name, email, address.dtoToModel());
    }

    public static List<CustomerDTO> modelToDTO(List<Customer> modelCustomers){
        return modelCustomers.stream().map(CustomerDTO::modelToDTO).collect(Collectors.toList());
    }

    public static CustomerDTO modelToDTO(Customer customer){
        return new CustomerDTO(
                customer.getName(),
                customer.getEmail(),
                AddressDTO.modelToDTO(customer.getAddress())
        );
    }
}
