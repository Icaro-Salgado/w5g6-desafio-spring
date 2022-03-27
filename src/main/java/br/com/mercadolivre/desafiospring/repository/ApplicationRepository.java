package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ApplicationRepository<T, K> {
    public List<T> read() throws DataBaseReadException;
    public Optional<T> find(K id) throws DataBaseReadException;
    public List<T> add(List<T> listToAdd) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists;
    public List<T> findBy(Map<String, Object> filters) throws DataBaseReadException;
}
