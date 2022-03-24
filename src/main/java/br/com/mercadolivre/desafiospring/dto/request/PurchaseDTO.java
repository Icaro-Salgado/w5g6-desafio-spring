package br.com.mercadolivre.desafiospring.dto.request;

import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    //TODO : transformar isso em uma lista de Products
    private List<Product> purchases;

    private Long purchaseId;
    private int clientId;
    private BigDecimal totalPrice;

    public List<Purchase> dtoToModel(){

        List<Purchase> newPurcheases = new ArrayList<Purchase>();
        newPurcheases.add(
                new Purchase(
                        this.purchaseId,
                        this.clientId,
                        this.purchases,
                        this.totalPrice
                )
        );
        return newPurcheases;

    }
    public PurchaseDTO (Purchase purchase){
        this.purchaseId = purchase.getPurchaseId();
        this.clientId   = purchase.getClientId();
        this.totalPrice = purchase.getTotalPrice();
        this.purchases = purchase.getProducts();
    }
}
