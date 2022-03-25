package br.com.mercadolivre.desafiospring.dto.response;

import br.com.mercadolivre.desafiospring.dto.request.AddressDTO;
import br.com.mercadolivre.desafiospring.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerErrorDTO implements ErrorDTO<Customer> {
    private String name;
    private String email;
    private AddressDTO address;
    private List<String> errors;

    @Override
    public CustomerErrorDTO modelToDTO(Customer model, List<String> errorMessages) {
        name = model.getName();
        email = model.getEmail();
        address = AddressDTO.modelToDTO(model.getAddress());
        errors = errorMessages;

        return this;
    }


}
