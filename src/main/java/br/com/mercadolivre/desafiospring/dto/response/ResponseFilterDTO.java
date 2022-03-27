package br.com.mercadolivre.desafiospring.dto.response;

import br.com.mercadolivre.desafiospring.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFilterDTO {

    private Long id;
    private String name;
    private Integer quantity;
    private	String category;
    private String brand;
    private BigDecimal price;
    private Boolean freeShipping;
    private String prestige;

    public ResponseFilterDTO(Product product)  {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.category = product.getCategory();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.freeShipping = product.getFreeShipping();
        this.prestige = product.getPrestige();
    }

}
