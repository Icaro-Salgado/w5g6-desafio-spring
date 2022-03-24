package br.com.mercadolivre.desafiospring.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PurchaseRequest {
    private List<Product> products;
}
