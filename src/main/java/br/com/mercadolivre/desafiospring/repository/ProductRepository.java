package br.com.mercadolivre.desafiospring.repository;

import br.com.mercadolivre.desafiospring.database.FileManager;
import br.com.mercadolivre.desafiospring.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements ApplicationRepository<Product, Long> {

    private final FileManager<Product[]> fileManager;
    private final String filename = "src/main/java/br/com/mercadolivre/desafiospring/database/products.json";

    @Override
    public List<Product> read() throws IOException {
        Product[] products = fileManager.readFromFile(filename, Product[].class);

        if (products.length == 0) {
            return new ArrayList<>();
        }
        return List.of(products);
    }

    @Override
    public Optional<Product> find(Long id) {
        return Optional.empty();
    }

    @Override
    public void add(List<Product> newProducts) throws IOException {
        List<Product> products = read();
        products.addAll(newProducts);

        fileManager.writeIntoFile(filename, products);
    }

}
