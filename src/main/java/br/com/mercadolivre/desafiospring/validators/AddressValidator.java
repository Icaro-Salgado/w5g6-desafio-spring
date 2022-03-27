package br.com.mercadolivre.desafiospring.validators;

import br.com.mercadolivre.desafiospring.models.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AddressValidator {

    public static String validateUF(Address address){
        if(address.getUf() != null && address.getUf().length() != 2){
            return "UF inválida";
        }
        return null;
    }

    public static String validateZipcode(Address address){
        if(address.getZipcode() != null && address.getZipcode().length() != 8){
            return "CEP inválido";
        }
        return null;
    }

    public static String missingFields(Address address){
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

}
