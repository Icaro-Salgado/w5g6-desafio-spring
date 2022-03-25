package br.com.mercadolivre.desafiospring.dto.response;

import br.com.mercadolivre.desafiospring.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDTO {
    private Long productId;
    private String name;
    private Integer quantity;

    public ResponseProductDTO(Product product)  {
       this.productId = product.getProductId();
       this.name = product.getName();
       this.quantity = product.getQuantity();
    }

}
