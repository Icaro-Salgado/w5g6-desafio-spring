package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.database.FileManager;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import br.com.mercadolivre.desafiospring.strategies.AlphabeticalSort;
import br.com.mercadolivre.desafiospring.strategies.PriceSort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ApplicationRepository<Product, Long> repo;

    public List<Product> addProducts(List<Product> products) throws IOException {
        repo.add(products);

        return products;
    }

    private List<Product> sortProducts(Integer sortStrategy, List<Product> productList) throws IOException {

        List<Product> products = productList;

        switch (sortStrategy) {
            case 0:
                return new AlphabeticalSort().sortAsc(products);
            case 1:
                return new AlphabeticalSort().sortDesc(products);
            case 2:
                return new PriceSort().sortDesc(products);
            case 3:
                return new PriceSort().sortAsc(products);
            default:
                return products;
        }
    }

    public List<Product> getProducts() throws IOException {
        List<Product> products = repo.read();

        return products;
    }

    public List<Product> filterBy(Map<String, Object> search) throws IOException, NoSuchMethodException {
        Object order;
        List<Product> products;

        if (search.containsKey("order")) {
            order = search.get("order");
            search.remove("order");
            products = repo.findBy(search);

            return sortProducts(Integer.parseInt(order.toString()), products);
        }

        return repo.findBy(search);
    }

    public Product findProduct(Long id) {
        return repo.find(id).orElse(new Product());
    }
}
