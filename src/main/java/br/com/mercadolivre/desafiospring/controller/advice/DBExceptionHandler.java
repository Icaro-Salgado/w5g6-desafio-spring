package br.com.mercadolivre.desafiospring.controller.advice;

import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DBExceptionHandler {

    @ExceptionHandler(value = {DataBaseWriteException.class})
    protected ResponseEntity<Object> handleWriteException(DataBaseWriteException ex) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = {DataBaseReadException.class})
    protected ResponseEntity<Object> handleReadException(DataBaseReadException ex) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = {DBEntryAlreadyExists.class})
    protected ResponseEntity<Object> handleDuplicateEntryException(DBEntryAlreadyExists ex) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }
}
