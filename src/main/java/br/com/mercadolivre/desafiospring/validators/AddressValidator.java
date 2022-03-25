package br.com.mercadolivre.desafiospring.validators;

import br.com.mercadolivre.desafiospring.dto.request.AddressDTO;
import br.com.mercadolivre.desafiospring.dto.response.AddressErrorDTO;
import br.com.mercadolivre.desafiospring.dto.response.CustomerErrorDTO;
import br.com.mercadolivre.desafiospring.models.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AddressValidator {

    private final Address address;

    private String validateUF(){
        if(address.getUf() != null && address.getUf().length() != 2){
            return "UF inválida";
        }
        return null;
    }

    private String validateZipcode(){
        if(address.getZipcode() != null && address.getZipcode().length() != 8){
            return "CEP inválido";
        }
        return null;
    }

    private String missingFields(){
        List<String> fieldNames = List.of(
                "street",
                "neighborhood",
                "uf",
                "country",
                "zipcode"
        );

        List<String> missingFields = fieldNames.stream().filter(field -> {
            try {
                Object result = address.getClass()
                        .getDeclaredMethod("get" + StringUtils.capitalize(field))
                        .invoke(address);
                return result == null;
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());

        if(!missingFields.isEmpty()){
            return "Os campos " + missingFields + " não foram preenchidos";
        }

        return null;

    }

    public AddressErrorDTO validate() {
        // validators
        Method[] declaredMethods = AddressValidator.class.getDeclaredMethods();
        List<String> errorMessages = new ArrayList<>();

        Arrays.stream(declaredMethods)
                .filter(m -> m.getModifiers() == Modifier.PRIVATE).collect(Collectors.toList())
                .forEach(m-> {
                    try {
                        Object errorMessage = m.invoke(this);
                        if(errorMessage != null){
                            errorMessages.add(errorMessage.toString());
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });

        if(!errorMessages.isEmpty()){
            AddressDTO addressObj = AddressDTO.modelToDTO(address);
            return new AddressErrorDTO(addressObj, errorMessages);
        }
        return null;
    }
}
