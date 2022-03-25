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
public class CustomerErrorDTO implements ErrorDTO{
    private String name;
    private String email;
    private AddressDTO address;
    private List<String> errors;

    @Override
    public CustomerErrorDTO modelToDTO(Object model, List<String> errorMessages) {
        Customer customer = (Customer) model;
        name = customer.getName();
        email = customer.getEmail();
        address = AddressDTO.modelToDTO(customer.getAddress());
        errors = errorMessages;

        return this;
    }

    @Override
    public void pushMessage(String errorMessage) {
        errors.add(errorMessage);

    }

    @Override
    public void pushMessage(List<String> errorMessages) {
        errors.addAll(errorMessages);
    }


}
