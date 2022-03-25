package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.request.ProductDTO;
import br.com.mercadolivre.desafiospring.dto.response.ProductsCreatedDTO;
import br.com.mercadolivre.desafiospring.dto.response.ResponseProductDTO;
import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class ProductController {

    final private ProductService productService;

    @PostMapping("insert-articles-request/")
    public ResponseEntity<ProductsCreatedDTO> InsertProducts(
            @RequestBody ProductDTO product, UriComponentsBuilder uriBuilder
    ) throws DataBaseReadException, DBEntryAlreadyExists, DataBaseWriteException {

        List<Product> productsCreated = productService.addProducts(product.dtoToModel());

        List<ResponseProductDTO> productsResponseDTO = productsCreated.stream().map(ResponseProductDTO::new).collect(Collectors.toList());
        ProductsCreatedDTO productsCreatedDTO = new ProductsCreatedDTO(productsResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productsCreatedDTO);
    }

    @GetMapping("articles/")
    public ResponseEntity<List<ResponseProductDTO>> retrieveProducts(
            @RequestParam("order") Optional<Integer> orderStrategy
    ) throws DataBaseReadException {
        List<Product> products;

        if (orderStrategy.isPresent()) {
            products = productService.sortProducts(orderStrategy.get());
        } else {
            products = productService.getProducts();
        }

        List<ResponseProductDTO> productsDTO = products.stream().map(ResponseProductDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(productsDTO);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Product> retrieveFindBy(@RequestParam Map<String, Object> parameters) throws NoSuchMethodException, DataBaseReadException {
        return productService.filterBy(parameters);

    }
}
