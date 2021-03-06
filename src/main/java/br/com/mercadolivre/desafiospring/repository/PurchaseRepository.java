package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.database.FileManager;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.utils.ClassUtils;
import br.com.mercadolivre.desafiospring.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PurchaseRepository implements ApplicationRepository<Purchase, Long> {


    final private String filename = "purchases.json";
    final private FileManager<Purchase[]> fileManager;

    @Override
    public List<Purchase> read() throws DataBaseReadException {
        Purchase[] purchases = fileManager.readFromFile(filename, Purchase[].class);

        if (purchases.length == 0) {
            return new ArrayList<>();
        }
        return Arrays.stream(purchases).collect(Collectors.toList());
    }

    @Override
    public Optional<Purchase> find(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Purchase> add(List<Purchase> listToAdd) throws DataBaseReadException, DataBaseWriteException {
        List<Purchase> purchases = read();

        purchases.addAll(listToAdd);

        fileManager.writeIntoFile(filename,purchases);
        return purchases;
    }

    @Override
    public List<Purchase> findBy(Map<String, Object> filters) throws DataBaseReadException {
        List<Purchase> purchases = Arrays.asList(fileManager.readFromFile(filename, Purchase[].class));

        for (var filter : filters.entrySet()) {
            return purchases.stream()
                    .filter(purchase -> {
                        Object value = ClassUtils.invokeGetMethod(purchase, filter.getKey());
                        return value instanceof String
                                ? ((String) value).equalsIgnoreCase((String) filter.getValue())
                                : value.equals(filter.getValue());
                    })
                    .collect(Collectors.toList());
        }
        return purchases;
    }

    @Override
    public Integer update(Map<String, Object> filters, Map<String, Object> values) throws DataBaseReadException, DataBaseWriteException {
        try{
            List<Purchase> purchases = findBy(filters);

            RepositoryUtils.substituteValues(purchases, values);

            List<Purchase> allPurchases = read();
            List<Purchase> productList = allPurchases.stream().map(p -> {
                Optional<Purchase> first = purchases.stream().filter(updated -> updated.getPurchaseId().equals(p.getPurchaseId())).findFirst();

                return first.isEmpty() ? p : first.get();
            }).collect(Collectors.toList());


            fileManager.writeIntoFile(filename, productList);

            return purchases.size();

        }catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
            return 0;
        }
    }

}
