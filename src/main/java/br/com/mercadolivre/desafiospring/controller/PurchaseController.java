package br.com.mercadolivre.desafiospring.controller;

import br.com.mercadolivre.desafiospring.dto.request.PurchaseDTO;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-request")
@AllArgsConstructor
public class PurchaseController {

    final private PurchaseService purchaseservice;


//    public List<Purchase> purchaseRequest(@RequestBody PurchaseDTO purchase) throws IOException {
//        return purchaseservice.addPurchase(purchase.dtoToModel());
//    }
    @PostMapping
    public String purchaseRequest(){
        return "Purchase is ok ";
    }
}
