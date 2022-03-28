package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.database.FileManager;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Customer;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.utils.ClassUtils;
import br.com.mercadolivre.desafiospring.utils.RepositoryUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.el.PropertyNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
                            return value instanceof String
                                    ? ((String) value).equalsIgnoreCase((String) filter.getValue())
                                    : value.equals(filter.getValue());
                        })
                        .collect(Collectors.toList());
            }

            return products;

        } catch (PropertyNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Product> add(List<Product> newProducts) throws DataBaseReadException, DataBaseWriteException {
        List<Product> products = read();
        products.addAll(newProducts);

        fileManager.writeIntoFile(filename, products);
        return products;
    }

    @Override
    public Optional<Product> find(Long id) throws DataBaseReadException {

            Product[] products = fileManager.readFromFile(filename, Product[].class);

            return Arrays.stream(products).filter(p -> p.getId().equals(id)).findFirst();

    }


    @Override
    public Integer update(Map<String, Object> filters, Map<String, Object> values) throws DataBaseReadException, DataBaseWriteException {
        try{
            List<Product> products = findBy(filters);

            RepositoryUtils.substituteValues(products, values);

            List<Product> allProducts = read();
            List<Product> productList = allProducts.stream().map(p -> {
                Optional<Product> first = products.stream().filter(updated -> updated.getId().equals(p.getId())).findFirst();

                return first.isEmpty() ? p : first.get();
            }).collect(Collectors.toList());


            fileManager.writeIntoFile(filename, productList);

            return products.size();

        }catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
            return 0;
        }

    }


}
