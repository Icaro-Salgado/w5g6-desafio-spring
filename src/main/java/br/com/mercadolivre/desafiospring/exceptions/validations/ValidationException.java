package br.com.mercadolivre.desafiospring.exceptions.validations;

public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }
}
