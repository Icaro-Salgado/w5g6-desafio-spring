package br.com.mercadolivre.desafiospring.dto.response;

import br.com.mercadolivre.desafiospring.dto.request.AddressDTO;
import br.com.mercadolivre.desafiospring.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressErrorDTO implements ErrorDTO{
    private AddressDTO address;
    private List<String> errors;

    @Override
    public ErrorDTO modelToDTO(Object model, List<String> errorMessages) {
        Address address = (Address) model;
        this.address = AddressDTO.modelToDTO(address);
        this.errors = errorMessages;
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
