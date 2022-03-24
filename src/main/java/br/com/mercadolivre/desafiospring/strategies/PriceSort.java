package br.com.mercadolivre.desafiospring.strategies;

import br.com.mercadolivre.desafiospring.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class PriceSort implements SortStrategy<Product> {

    @Override
    public List<Product> sortAsc(List<Product> productsToSort) {
        return productsToSort.stream()
                .sorted((itemX, itemY)-> itemX.getPrice().compareTo(itemY.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortDesc(List<Product> productsToSort) {
        return productsToSort.stream()
                .sorted((itemX, itemY)-> itemY.getPrice().compareTo(itemX.getPrice()))
                .collect(Collectors.toList());
    }
}
