package br.com.mercadolivre.desafiospring.controller.advice;

import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.exceptions.validations.OutOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DBExceptionHandler {

    @ExceptionHandler(value = {DataBaseWriteException.class})
    protected ResponseEntity<Object> handleWriteException(DataBaseWriteException ex) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.internalServerError().body("");
    }

    @ExceptionHandler(value = {DataBaseReadException.class})
    protected ResponseEntity<Object> handleReadException(DataBaseReadException ex) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.internalServerError().body("");
    }

    @ExceptionHandler(value = {DBEntryAlreadyExists.class})
    protected ResponseEntity<Object> handleDuplicateEntryException(DBEntryAlreadyExists ex) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body("");
    }

    @ExceptionHandler(value = {OutOfStockException.class})
    protected ResponseEntity<Object> handleOutOfStockException(OutOfStockException ex) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }
}
