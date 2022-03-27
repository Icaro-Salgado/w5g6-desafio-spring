package br.com.mercadolivre.desafiospring.controller;


import br.com.mercadolivre.desafiospring.dto.request.CartDTO;
import br.com.mercadolivre.desafiospring.dto.request.PurchaseDTO;
import br.com.mercadolivre.desafiospring.dto.request.PurchaseRequestDTO;
import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.exceptions.validations.OutOfStockException;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/purchase-request")
@AllArgsConstructor
public class PurchaseController {

    final private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<List<PurchaseDTO>> purchaseRequest(
            @RequestBody PurchaseRequestDTO purchase
    ) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists, OutOfStockException, NoSuchMethodException {

        List<Purchase> requestFromPurchases = purchaseService.addPurchaseFromRequest(purchase.dtoToModel());

        return ResponseEntity.ok(requestFromPurchases.stream().map(PurchaseDTO::new).collect(Collectors.toList()));

    }

    @GetMapping("/cart/{customerId}")
    public CartDTO effectiveCart(
            @PathVariable Long customerId
    ) throws DataBaseReadException {
        List<Purchase> purchases = purchaseService.findCustomerPurchases(customerId);
        CartDTO cart = new CartDTO();

        if (purchases.isEmpty()) {
            return cart;
        }

        cart.setProducts(purchases.get(0).getProducts());
        cart.setTotal(purchaseService.effectiveCustomerCart(purchases));
        return cart;
    }

}
