package br.com.mercadolivre.desafiospring.dto.request;

import br.com.mercadolivre.desafiospring.models.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private List<Purchase> purchases;

    public List<Purchase> dtoToModel(){

        return purchases.stream().map(p -> new Purchase(
                p.getPurchaseId(),
                p.getClientId(),
                p.getProducts(),
                p.getTotalPrice()
        )).collect(Collectors.toList());
    }
}
