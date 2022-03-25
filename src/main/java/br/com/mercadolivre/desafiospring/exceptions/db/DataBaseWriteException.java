package br.com.mercadolivre.desafiospring.exceptions.db;

public class DataBaseWriteException extends Exception {

    public DataBaseWriteException(String msg) {
        super(msg);
    }
}
