package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.database.FileManager;
import br.com.mercadolivre.desafiospring.models.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PurchaseRepository implements ApplicationRepository<Purchase, Long>{


    final private  String filename = "src/main/java/br/com/mercadolivre/desafiospring/database/purchases.json";
    final private  FileManager<Purchase[]> fileManager;

    @Override
    public List<Purchase> read() throws IOException {
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
    public void add(List<Purchase> listToAdd) throws IOException {
        List<Purchase> purchases = read();

        purchases.addAll(listToAdd);

        fileManager.writeIntoFile(filename,purchases);
    }
}
