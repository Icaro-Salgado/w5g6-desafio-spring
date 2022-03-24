package br.com.mercadolivre.desafiospring.repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository<T, K> {
    public List<T> read() throws IOException;
    public Optional<T> find(K id);
    public void add(List<T> listToAdd) throws IOException;
}
