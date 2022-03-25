package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import br.com.mercadolivre.desafiospring.strategies.AlphabeticalSort;
import br.com.mercadolivre.desafiospring.strategies.PriceSort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ApplicationRepository<Product, Long> repo;

    public List<Product> addProducts(List<Product> products) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists {
        List<Product> existingProducts = repo.read();
        List<Product> createdProducts = new ArrayList<>();

        products.forEach(newProduct -> {
            newProduct.setId(existingProducts.size() + 1L);
            existingProducts.add(newProduct);
            createdProducts.add(newProduct);
        });

        repo.add(existingProducts);
        return createdProducts;
    }

    public List<Product> sortProducts(Integer sortStrategy) throws DataBaseReadException {

        List<Product> products = repo.read();

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

    public List<Product> getProducts() throws DataBaseReadException {
        List<Product> products = repo.read();

        return products;
    }

    public List<Product> filterBy(Map<String, Object> search) throws NoSuchMethodException, DataBaseReadException {
        return repo.findBy(search);
    }

    public Product findProduct(Long id) throws DataBaseReadException {
        return repo.find(id).orElse(new Product());
    }
}
