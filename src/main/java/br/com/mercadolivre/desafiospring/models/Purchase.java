package br.com.mercadolivre.desafiospring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    private Long purchaseId;
    private int clientId;//recive client id model
    private List<Product> products;
    private BigDecimal totalPrice;
}