package br.com.mercadolivre.desafiospring.strategies;

import br.com.mercadolivre.desafiospring.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class AlphabeticalSort implements SortStrategy<Product> {

    @Override
    public List<Product> sortAsc(List<Product> productsToSort) {
        return productsToSort.stream()
                .sorted((itemX, itemY)-> itemX.getName().compareTo(itemY.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortDesc(List<Product> products) {
        return products.stream()
                .sorted((itemX, itemY)-> itemY.getName().compareTo(itemX.getName()))
                .collect(Collectors.toList());
    }
}
