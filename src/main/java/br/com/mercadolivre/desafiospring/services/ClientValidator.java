package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.models.Client;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class ClientValidator {

    private final Client client;

    private boolean validateEmail() {
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return Pattern.compile(regexPattern).matcher(client.getEmail()).matches();
    }

    public void validate() {
        // validators
        if (!validateEmail()) {
            throw new IllegalArgumentException();
        }
    }
}
