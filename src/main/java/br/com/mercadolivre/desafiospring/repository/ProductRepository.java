package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.database.FileManager;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.utils.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.el.PropertyNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements ApplicationRepository<Product, Long> {

    private final FileManager<Product[]> fileManager;
    private final String filename = "products.json";

    @Override
    public List<Product> read() throws DataBaseReadException {
        Product[] products = fileManager.readFromFile(filename, Product[].class);

        if (products.length == 0) {
            return new ArrayList<>();
        }
        return Arrays.stream(products).collect(Collectors.toList());
    }

    @Override
    public List<Product> findBy(Map<String, Object> filters) throws DataBaseReadException {
        try {
            List<Product> products = Arrays.asList(fileManager.readFromFile(filename, Product[].class));

            for (var filter : filters.entrySet()) {
                products = products.stream()
                        .filter(client -> {
                            Object value = ClassUtils.invokeGetMethod(client, filter.getKey());
                            return value.equals(filter.getValue());
                        })
                        .collect(Collectors.toList());
            }

            return products;

        } catch (PropertyNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Product> find(Long id) {
        return Optional.empty();
    }

    @Override
    public void add(List<Product> updateProducts) throws DataBaseReadException, DataBaseWriteException {
        fileManager.writeIntoFile(filename, updateProducts);
    }

}
