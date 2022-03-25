package br.com.mercadolivre.desafiospring.validators;

import br.com.mercadolivre.desafiospring.dto.response.AddressErrorDTO;
import br.com.mercadolivre.desafiospring.dto.response.CustomerErrorDTO;
import br.com.mercadolivre.desafiospring.dto.response.ErrorDTO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Validator {

    public static <T> ErrorDTO<?> validate(Class<?> validator, T instanceToValidate, ErrorDTO<T> returnDTO) {
        // validators
        Method[] declaredMethods = validator.getDeclaredMethods();
        List<String> errorMessages = new ArrayList<>();

        Arrays.stream(declaredMethods)
                .filter(m -> m.getModifiers() == Modifier.PRIVATE).collect(Collectors.toList())
                .forEach(m-> {
                    try {
                        Object errorMessage = m.invoke(instanceToValidate);
                        if(errorMessage != null){
                            errorMessages.add(errorMessage.toString());
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });

        if(!errorMessages.isEmpty()){
            return returnDTO.modelToDTO(instanceToValidate, errorMessages);
        }
        return null;
    }
}
