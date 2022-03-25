package br.com.mercadolivre.desafiospring.validators;

import br.com.mercadolivre.desafiospring.dto.request.AddressDTO;
import br.com.mercadolivre.desafiospring.dto.response.AddressErrorDTO;
import br.com.mercadolivre.desafiospring.dto.response.CustomerErrorDTO;
import br.com.mercadolivre.desafiospring.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomerValidator {

    private final Customer customer;

    private String validateEmail() {
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        boolean isValid = Pattern.compile(regexPattern).matcher(customer.getEmail()).matches();

        if (!isValid) {
            System.out.println("not valid");
            return "Email " + customer.getEmail() + " não é válido.";
        }
        return null;
    }


    private String missingFields() {
        List<String> fieldNames = List.of(
                "name",
                "email"
        );

        List<String> missingFields = fieldNames.stream().filter(field -> {
            try {
                Object result = customer.getClass()
                        .getDeclaredMethod("get" + StringUtils.capitalize(field))
                        .invoke(customer);
                return result == null;
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());

        if (!missingFields.isEmpty()) {
            return "Os campos " + missingFields + " não foram preenchidos";
        }

        return null;

    }

    public CustomerErrorDTO validate() {
        // validators
        Method[] declaredMethods = CustomerValidator.class.getDeclaredMethods();
        List<String> errorMessages = new ArrayList<>();

        Arrays.stream(declaredMethods)
                .filter(m -> m.getModifiers() == Modifier.PRIVATE).collect(Collectors.toList())
                .forEach(m -> {
                    try {
                        Object errorMessage = m.invoke(this);
                        if (errorMessage != null) {
                            errorMessages.add(errorMessage.toString());
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });

        AddressValidator addressValidator = new AddressValidator(customer.getAddress());
        AddressErrorDTO addressErrors = addressValidator.validate();

        if (addressErrors != null) {
            errorMessages.addAll(addressErrors.getErrors());
        }


        if (!errorMessages.isEmpty()) {
            return new CustomerErrorDTO(
                    customer.getName(),
                    customer.getEmail(),
                    AddressDTO.modelToDTO(customer.getAddress()),
                    errorMessages);
        }
        return null;
    }

}
