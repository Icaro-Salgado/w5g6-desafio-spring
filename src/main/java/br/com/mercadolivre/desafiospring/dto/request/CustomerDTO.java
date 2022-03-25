package br.com.mercadolivre.desafiospring.dto.request;

import br.com.mercadolivre.desafiospring.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String email;
    private AddressDTO address;

    public Customer dtoToModel(){
        return new Customer(name, email, address.dtoToModel());
    }

    public static CustomerDTO modelToDTO(Customer customer){
        return new CustomerDTO(
                customer.getName(),
                customer.getEmail(),
                AddressDTO.modelToDTO(customer.getAddress())
        );
    }
}
