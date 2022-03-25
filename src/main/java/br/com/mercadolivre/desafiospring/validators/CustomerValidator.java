package br.com.mercadolivre.desafiospring.validators;

import br.com.mercadolivre.desafiospring.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomerValidator {


    public static String validateEmail(Customer customer) {
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        boolean isValid = Pattern.compile(regexPattern).matcher(customer.getEmail()).matches();

        if (!isValid) {
            System.out.println("not valid");
            return "Email " + customer.getEmail() + " não é válido.";
        }
        return null;
    }

    public static String missingFields(Customer customer) {
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

}
