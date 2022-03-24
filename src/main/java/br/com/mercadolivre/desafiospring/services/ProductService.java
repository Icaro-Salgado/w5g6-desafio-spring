package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ApplicationRepository<Product, Long> repo;

    public List<Product> addProducts(List<Product> products) throws IOException {
        repo.add(products);

        return products;
    }
}