package br.com.mercadolivre.desafiospring.validators;

import br.com.mercadolivre.desafiospring.dto.response.ErrorDTO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidatorService {

    public static <T> ErrorDTO validateObject(Class<?> validator, Object instanceToValidate, ErrorDTO returnDTO) {
        // validators
        Method[] declaredMethods = validator.getDeclaredMethods();
        List<String> errorMessages = new ArrayList<>();


        Arrays.stream(declaredMethods)
                .filter(m ->  m.getModifiers() == (Modifier.PUBLIC | Modifier.STATIC)
                )
                .forEach(m-> {
                    try {
                        Object errorMessage = m.invoke(validator, instanceToValidate);
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
