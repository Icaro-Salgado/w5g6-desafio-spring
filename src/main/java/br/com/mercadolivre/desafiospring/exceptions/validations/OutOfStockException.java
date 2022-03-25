package br.com.mercadolivre.desafiospring.exceptions.validations;

public class OutOfStockException extends Exception {

    public OutOfStockException(String message) {
        super(message);
    }
}
