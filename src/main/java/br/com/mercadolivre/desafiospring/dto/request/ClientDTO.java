package br.com.mercadolivre.desafiospring.dto.request;

import br.com.mercadolivre.desafiospring.models.Address;
import br.com.mercadolivre.desafiospring.models.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDTO {
    private String name;
    private String email;
    private AddressDTO address;

    public Client dtoToModel(){
        return new Client(name, email, address.dtoToModel());
    }

    public static ClientDTO modelToDTO(Client client){
        return new ClientDTO(
                client.getName(),
                client.getEmail(),
                AddressDTO.modelToDTO(client.getAddress())
        );
    }
}
