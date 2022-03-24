package br.com.mercadolivre.desafiospring.dto.request;

import br.com.mercadolivre.desafiospring.models.Address;
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

    public Address dtoToModel(){
        return new Address(
                street,
                neighborhood,
                uf,
                country,
                zipcode
        );
    }

    public static AddressDTO modelToDTO(Address addressModel){
        return new AddressDTO(
                addressModel.getStreet(),
                addressModel.getNeighborhood(),
                addressModel.getUf(),
                addressModel.getCountry(),
                addressModel.getZipcode()
        );
    }

}
