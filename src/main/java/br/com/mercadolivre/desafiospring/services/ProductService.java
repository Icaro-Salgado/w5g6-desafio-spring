package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import br.com.mercadolivre.desafiospring.strategies.AlphabeticalOrdering;
import br.com.mercadolivre.desafiospring.strategies.OrderingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ApplicationRepository<Product, Long> repo;

    public List<Product> addProducts(List<Product> products) throws IOException {
        repo.add(products);

        return products;
    }

    public List<Product> retrieveOrderedProducts(int orderStrategy) throws IOException {

        List<Product> products = repo.read();

        if (orderStrategy == 0) {
            return new AlphabeticalOrdering().sortAsc(products);
        } else if (orderStrategy == 1) {
            return new AlphabeticalOrdering().sortDesc(products);
        }
        return products;
    }
}