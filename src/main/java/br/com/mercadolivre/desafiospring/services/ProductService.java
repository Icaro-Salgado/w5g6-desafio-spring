package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.database.FileManager;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import br.com.mercadolivre.desafiospring.strategies.AlphabeticalSort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ApplicationRepository<Product, Long> repo;
    List<Product> productList = new ArrayList<>();

    public List<Product> addProducts(List<Product> products) throws IOException {
        repo.add(products);

        return products;
    }

    public List<Product> sortProducts(Integer sortStrategy) throws IOException {

        List<Product> products = repo.read();

        if (sortStrategy == 0) {
            return new AlphabeticalSort().sortAsc(products);
        } else if (sortStrategy == 1) {
            return new AlphabeticalSort().sortDesc(products);
        }
        return products;
    }

    public List<Product> filterByCategory(String category) throws IOException {
        List<Product> products = repo.read();

        return products.stream()
                .filter(cat -> cat.getCategory().equals(category))
                .collect(Collectors.toList());
        
    }
}