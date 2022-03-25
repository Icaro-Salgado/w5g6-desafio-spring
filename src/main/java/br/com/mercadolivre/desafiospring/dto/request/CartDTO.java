package br.com.mercadolivre.desafiospring.dto.request;

import br.com.mercadolivre.desafiospring.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDTO {

    private List<Product> products;
    private BigDecimal total;

}
