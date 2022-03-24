package br.com.mercadolivre.desafiospring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {
    private Long id;
    private String name;
    private String email;
    private Address address;

    public Client(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Client)){
            return false;
        }
        return email.equals(((Client) obj).email);
    }
}
