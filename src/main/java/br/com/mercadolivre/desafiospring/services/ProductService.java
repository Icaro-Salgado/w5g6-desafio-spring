package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import br.com.mercadolivre.desafiospring.strategies.AlphabeticalSort;
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

    public List<Product> sortProducts(int sortStrategy) throws IOException {

        List<Product> products = repo.read();

        if (sortStrategy == 0) {
            return new AlphabeticalSort().sortAsc(products);
        } else if (sortStrategy == 1) {
            return new AlphabeticalSort().sortDesc(products);
        }
        return products;
    }
}