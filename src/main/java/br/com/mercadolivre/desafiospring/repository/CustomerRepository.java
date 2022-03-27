package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.database.FileManager;
import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Customer;
import br.com.mercadolivre.desafiospring.utils.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.el.PropertyNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CustomerRepository implements ApplicationRepository<Customer, Long> {

    private final FileManager<Customer[]> fileManager;
    private final String filename = "customers.json";

    @Override
    public List<Customer> read() throws DataBaseReadException {
        Customer[] customers = fileManager.readFromFile(filename, Customer[].class);

        if (customers.length == 0) {
            return new ArrayList<>();
        }
        return Arrays.stream(customers).collect(Collectors.toList());
    }

    /*
        parameters e.g. findBy(Map.of("name", "value"));
        You also can pass a subfield to the filter, use when you want to filter from dependency class.
        e.g. Person has field address from Address class and you want to filter by country
        findBy(Map.of("address.country", "Brazil"));

        *** for now, only works with 1 level ***

        For each received filter, will check if field exists in class, calling getClassFields to receive a list of fields from class
        then, will call the get method for the field. If the filter is from subfield, will check if dependency class has field and then,
        call get method again, but from dependency class

     */
    @Override
    public List<Customer> findBy(Map<String, Object> filters) throws DataBaseReadException {
        try {
            List<Customer> customers = Arrays.asList(fileManager.readFromFile(filename, Customer[].class));

            for (var filter : filters.entrySet()) {
                customers = customers.stream()
                        .filter(client -> {
                            Object value = ClassUtils.invokeGetMethod(client, filter.getKey());
                            return value instanceof String
                                    ? ((String) value).equalsIgnoreCase((String) filter.getValue())
                                    : value.equals(filter.getValue());
                        })
                        .collect(Collectors.toList());
            }

            return customers;

        } catch (PropertyNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Customer> find(Long id) throws DataBaseReadException {
        Customer[] customers = fileManager.readFromFile(filename, Customer[].class);

        return Arrays.stream(customers).filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public List<Customer> add(List<Customer> listToAdd) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists {
        List<Customer> customers = read();
        for (var clientToAdd : listToAdd) {
            if (customers.contains(clientToAdd)) {

                throw new DBEntryAlreadyExists("Cliente " + clientToAdd.getEmail() + "já cadastrado na base");
            }
            clientToAdd.setId((long) (customers.size() + 1));
            customers.add(clientToAdd);
        }

        fileManager.writeIntoFile(filename, customers);
        return listToAdd;
    }
}
