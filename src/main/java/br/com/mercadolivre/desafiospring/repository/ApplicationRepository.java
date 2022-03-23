package br.com.mercadolivre.desafiospring.repository;

import java.util.List;
import java.util.Optional;


public interface ApplicationRepository<T> {
    public List<T> read();
    public Optional<T> find(int id);
    public void add(T toAdd);
    public void add(List<T> listToAdd);
}