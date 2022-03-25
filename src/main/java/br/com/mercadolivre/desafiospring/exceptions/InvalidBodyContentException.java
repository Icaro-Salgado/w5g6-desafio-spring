package br.com.mercadolivre.desafiospring.exceptions;

import lombok.Data;

@Data
public class InvalidBodyContentException extends RuntimeException{

    private Object invalidBody;

    public InvalidBodyContentException(Object body) {
        super(body.toString());
        this.invalidBody = body;
    }
}
