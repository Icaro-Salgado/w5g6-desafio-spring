package br.com.mercadolivre.desafiospring.exceptions.db;

public class DataBaseReadException extends Exception{

    public DataBaseReadException(String msg) {
        super(msg);
    }
}
