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


    private List<Product> sortProducts(Integer sortStrategy, List<Product> productList) throws DataBaseReadException {

        switch (sortStrategy) {
            case 0:
                return new AlphabeticalSort().sortAsc(productList);
            case 1:
                return new AlphabeticalSort().sortDesc(productList);
            case 2:
                return new PriceSort().sortDesc(productList);
            case 3:
                return new PriceSort().sortAsc(productList);
            default:
                return productList;
        }
    }

    public List<Product> getProducts() throws DataBaseReadException {

        return repo.read();
    }

    public List<Product> filterBy(Map<String, Object> search) throws DataBaseReadException {
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
