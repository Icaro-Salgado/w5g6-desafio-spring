package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.request.ProductDTO;
import br.com.mercadolivre.desafiospring.dto.response.ProductsCreatedDTO;
import br.com.mercadolivre.desafiospring.dto.response.ResponseProductDTO;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class ProductController {

    final private ProductService productService;

    @PostMapping("insert-articles-request/")
    public ResponseEntity<ProductsCreatedDTO> InsertProducts(@RequestBody ProductDTO product, UriComponentsBuilder uriBuilder) throws IOException {
        List<Product> productsCreated = productService.addProducts(product.dtoToModel());

        List<ResponseProductDTO> productsResponseDTO = productsCreated.stream().map(ResponseProductDTO::new).collect(Collectors.toList());
        ProductsCreatedDTO productsCreatedDTO = new ProductsCreatedDTO(productsResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productsCreatedDTO);
    }

    @GetMapping("articles/")
    public ResponseEntity<List<Product>> retrieveProducts(@RequestParam("order") Optional<Integer> orderStrategy) throws IOException {
        if (orderStrategy.isPresent()) {
            return ResponseEntity.ok(productService.sortProducts(orderStrategy.get()));
        }
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("category/")
    public ResponseEntity<List<Product>> retrieveFilterByCategory(@RequestParam String category) throws IOException {

        return ResponseEntity.ok(productService.filterByCategory(category));
    }
}
