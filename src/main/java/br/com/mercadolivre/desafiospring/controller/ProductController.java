package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.request.ProductDTO;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class ProductController {

    final private ProductService productService;

    @PostMapping
    public List<Product> InsertProducts(@RequestBody ProductDTO product) throws IOException {
        return productService.addProducts(product.dtoToModel());
    }

    @GetMapping("articles")
    public List<Product> retrieveSortedProducts(@RequestParam("order") Optional<Integer> orderStrategy) throws IOException {
        if (orderStrategy.isPresent()) {
            return productService.sortProducts(orderStrategy.get());
        }
        // depends on #7
        return new ArrayList<>();
    }
}
