package br.com.mercadolivre.desafiospring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private String street;
    private String neighborhood;
    private String uf;
    private String country;
    private String zipcode;

}
