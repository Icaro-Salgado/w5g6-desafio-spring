package br.com.mercadolivre.desafiospring.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @JsonProperty("productId")
    private Long id;
    private String name;
    private	String category;
    private String brand;
    private BigDecimal price;
    private Integer	quantity;
    private Boolean freeShipping;
    private String prestige;

}
