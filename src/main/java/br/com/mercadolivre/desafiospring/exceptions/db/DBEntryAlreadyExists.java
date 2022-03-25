package br.com.mercadolivre.desafiospring.exceptions.db;

public class DBEntryAlreadyExists extends Exception{
    public DBEntryAlreadyExists(String msg) {
        super(msg);
    }
}
