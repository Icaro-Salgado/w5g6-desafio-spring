package br.com.mercadolivre.desafiospring.dto.response;

import br.com.mercadolivre.desafiospring.dto.request.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AddressErrorDTO {
    private AddressDTO address;
    private List<String> errors;
}
