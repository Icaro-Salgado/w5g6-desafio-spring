package br.com.mercadolivre.desafiospring.dto.response;

import br.com.mercadolivre.desafiospring.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDTO {
    private Long id;
    private String name;
    private Integer quantity;

    public ResponseProductDTO(Product product)  {
       this.id = product.getId();
       this.name = product.getName();
       this.quantity = product.getQuantity();
    }

}
