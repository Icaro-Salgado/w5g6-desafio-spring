package br.com.mercadolivre.desafiospring.services;


import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.models.PurchaseRequest;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    final private ApplicationRepository<Purchase, Long> repo;
    final private ApplicationRepository<Product, Long> productRepo;

    public Boolean validate(List<Purchase> purchases) throws DataBaseReadException {

        for (Purchase purchase:
             purchases) {
            for (Product product:
                 purchase.getProducts()) {
                Optional<Product> inventory = productRepo.find(product.getProductId());
                if (inventory.isPresent()) {
                    if (inventory.get().getQuantity() < product.getQuantity()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public List<Purchase> addPurchase(List<Purchase> purchase) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists {
        repo.add(purchase);
        return purchase;
    }

    public List<Purchase> addPurchaseFromRequest(List<PurchaseRequest> purchaserequest) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists {
        //TODO: transformar em classe de conversão
        List<Purchase> newpurchases = purchaserequest.stream().map(p ->
                new Purchase(0L, 0L, p.getProducts(), new BigDecimal(0))
        ).collect(Collectors.toList());

        // validate inventory number
        if (!validate(newPurchases)) {
            System.out.println("Número de itens no estoque é inferior ao requisitado.");
            return new ArrayList<Purchase>();
        }
        repo.add(newPurchases);
        return newPurchases;
    }

    public List<Purchase> findCustomerPurchases(Long customerId) throws NoSuchMethodException, DataBaseReadException {
        return repo.findBy(Map.of("customerId", customerId));
    }

    public BigDecimal effectiveCustomerCart(List<Purchase> purchases) {
        BigDecimal total = BigDecimal.ZERO;

        if (purchases.size() > 0) {
            List<Product> products = purchases.get(0).getProducts();
            total = products.stream()
                    .map(p -> p.getPrice() == null ? BigDecimal.valueOf(0) : p.getPrice())
                    .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        }

        return total;
    }
}
