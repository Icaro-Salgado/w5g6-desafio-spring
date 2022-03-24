package br.com.mercadolivre.desafiospring.strategies;

import java.util.List;

public interface SortStrategy<T>{

    List<T> sortAsc(List<T> listToOrder);
    List<T> sortDesc(List<T> listToOrder);

}
