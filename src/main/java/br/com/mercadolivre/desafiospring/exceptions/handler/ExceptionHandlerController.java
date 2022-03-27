package br.com.mercadolivre.desafiospring.exceptions.handler;

import br.com.mercadolivre.desafiospring.exceptions.InvalidBodyContentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = InvalidBodyContentException.class)
    protected ResponseEntity<Object> handleInvalidBodyException(InvalidBodyContentException ex) {
        return ResponseEntity.badRequest().body(ex.getInvalidBody());
    }
}
