package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ApplicationRepository<Product, Long> repo;

    private void addProduct(Product product) {
       repo.add(product);
    }

    public List<Product> addProducts(List<Product> products) {
        products.forEach(this::addProduct);

        return products;
    }
}