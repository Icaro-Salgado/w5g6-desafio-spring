package br.com.mercadolivre.desafiospring.exceptions;

import lombok.Data;

@Data
public class InvalidBodyContentException extends RuntimeException{

    private static final long serialVersionUID = -1957137827451037216L;
    private Object invalidBody;

    public InvalidBodyContentException(Object body) {
        super(body.toString());
        this.invalidBody = body;
    }
}
