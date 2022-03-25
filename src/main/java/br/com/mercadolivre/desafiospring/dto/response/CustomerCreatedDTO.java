package br.com.mercadolivre.desafiospring.dto.response;

import br.com.mercadolivre.desafiospring.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;

@AllArgsConstructor
@Data
public class CustomerCreatedDTO {

    private String id;
    private String name;
    private String email;
    private URI uri;

    public static CustomerCreatedDTO modelToDTO(Customer customer, URI uri){
        return new CustomerCreatedDTO(
                customer.getId().toString(),
                customer.getName(),
                customer.getEmail(),
                uri
        );
    }
}
