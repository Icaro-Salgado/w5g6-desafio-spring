package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.request.ProductDTO;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
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
    public List<Product> InsertProducts(@RequestBody ProductDTO product) throws IOException {
        return productService.addProducts(product.dtoToModel());
    }

    @GetMapping("articles")
    public List<Product> retrieveProducts(@RequestParam("order") Optional<Integer> orderStrategy) throws IOException {
        if (orderStrategy.isPresent()) {
            return productService.sortProducts(orderStrategy.get());
        }
        return productService.getProducts();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> testUrl(@RequestParam Map<String, Object> parameters) throws IOException, NoSuchMethodException {
        return productService.filterBy(parameters);
    }
}
