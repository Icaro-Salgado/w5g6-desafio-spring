package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.models.Customer;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class CustomerValidator {

    private final Customer customer;

    private boolean validateEmail() {
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return Pattern.compile(regexPattern).matcher(customer.getEmail()).matches();
    }

    public void validate() {
        // validators
        if (!validateEmail()) {
            throw new IllegalArgumentException();
        }
    }
}
