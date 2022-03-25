package br.com.mercadolivre.desafiospring.controller;


import br.com.mercadolivre.desafiospring.dto.request.PurchaseDTO;
import br.com.mercadolivre.desafiospring.dto.request.PurchaseRequestDTO;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/purchase-request")
@AllArgsConstructor
public class PurchaseController {

    final private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<List<PurchaseDTO>> purchaseRequest(@RequestBody PurchaseRequestDTO purchase) throws IOException {
        List<Purchase> requestFromPurchases = purchaseService.addPurchaseFromRequest(purchase.dtoToModel());

        return ResponseEntity.ok(requestFromPurchases.stream().map(PurchaseDTO::new).collect(Collectors.toList()));

    }

}
