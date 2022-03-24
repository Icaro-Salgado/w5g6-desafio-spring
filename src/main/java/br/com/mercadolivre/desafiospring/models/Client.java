package br.com.mercadolivre.desafiospring.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private Long id;
    private String name;
    private String email;
    private Address address;

    public Client(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
