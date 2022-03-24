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
        Address addressModel = new Address(
                address.getStreet(),
                address.getNeighborhood(),
                address.getUf(),
                address.getCountry(),
                address.getZipcode()
        );

        return new Client(name, email, addressModel);
    }

    public static ClientDTO modelToDTO(Client client){
        Address modelAddress = client.getAddress();
        AddressDTO addressDTO = new AddressDTO(
                modelAddress.getStreet(),
                modelAddress.getNeighborhood(),
                modelAddress.getUf(),
                modelAddress.getCountry(),
                modelAddress.getZipcode()
        );

        return new ClientDTO(
                client.getName(),
                client.getEmail(),
                addressDTO
        );
    }
}
