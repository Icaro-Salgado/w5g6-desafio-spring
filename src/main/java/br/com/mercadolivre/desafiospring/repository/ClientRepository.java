package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.database.FileManager;
import br.com.mercadolivre.desafiospring.models.Client;
import br.com.mercadolivre.desafiospring.utils.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.el.PropertyNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ClientRepository implements ApplicationRepository<Client, Long>{

    private final FileManager<Client[]> fileManager;
    private final String filename = "src/main/java/br/com/mercadolivre/desafiospring/database/clients.json";

    @Override
    public List<Client> read() throws IOException {
        Client[] clients = fileManager.readFromFile(filename, Client[].class);

        if (clients.length == 0) {
            return new ArrayList<>();
        }
        return Arrays.stream(clients).collect(Collectors.toList());
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
    public List<Client> findBy(Map<String, Object> filters) throws IOException, NoSuchMethodException {
        try{
            List<Client> clients = Arrays.asList(fileManager.readFromFile(filename, Client[].class));

            for(var filter :filters.entrySet()){
                clients = clients.stream()
                        .filter(client -> {
                            Object value = ClassUtils.invokeGetMethod(client, filter.getKey());
                            return value.equals(filter.getValue());
                        })
                        .collect(Collectors.toList());
            }

            return clients;

        }catch (PropertyNotFoundException e){
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Client> find(Long id) {
        try {
            Client[] clients = fileManager.readFromFile(filename, Client[].class);

            return Arrays.stream(clients).filter(c -> c.getId().equals(id)).findFirst();

        }catch (IOException e){
            return Optional.empty();
        }

    }

    @Override
    public void add(List<Client> listToAdd) throws IOException {
        List<Client> clients = read();
        for(var clientToAdd: listToAdd){
            if (clients.contains(clientToAdd)) {
                throw new FileAlreadyExistsException("Cliente " + clientToAdd.getEmail() +  "já cadastrado na base");
            }
            clientToAdd.setId((long) (clients.size() + 1));
            clients.add(clientToAdd);
        }

        fileManager.writeIntoFile(filename, clients);
    }
}
