package br.com.mercadolivre.desafiospring.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String street;
    private String neighborhood;
    private String uf;
    private String country;
    private String zipcode;

}
