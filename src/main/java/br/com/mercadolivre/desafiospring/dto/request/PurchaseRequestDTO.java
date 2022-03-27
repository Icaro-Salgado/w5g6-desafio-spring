package br.com.mercadolivre.desafiospring.dto.request;

import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.models.PurchaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDTO {

    private Long customerId;
    private List<Product> purchases;



    public List<PurchaseRequest> dtoToModel(){

        List<PurchaseRequest> purchasesrequest = new ArrayList<PurchaseRequest>();

        purchasesrequest.add(
                new PurchaseRequest(this.customerId,this.purchases)
             );
        return purchasesrequest;

    }
}
