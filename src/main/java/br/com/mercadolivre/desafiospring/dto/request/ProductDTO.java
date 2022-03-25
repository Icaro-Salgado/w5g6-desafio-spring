package br.com.mercadolivre.desafiospring.dto.request;
import br.com.mercadolivre.desafiospring.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

     private List <Product> products;

     public  List<Product> dtoToModel(){

         return products.stream().map(a -> new Product(a.getProductId(),
                 a.getName(),
                 a.getCategory(),
                 a.getBrand(),
                 a.getPrice(),
                 a.getQuantity(),
                 a.getFreeShipping(),
                 a.getPrestige()
         )).collect(Collectors.toList());
     }
}
