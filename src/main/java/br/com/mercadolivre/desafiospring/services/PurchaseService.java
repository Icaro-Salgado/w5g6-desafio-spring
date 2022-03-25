package br.com.mercadolivre.desafiospring.services;


import br.com.mercadolivre.desafiospring.exceptions.db.DBEntryAlreadyExists;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.models.PurchaseRequest;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    final private ApplicationRepository<Purchase, Long> repo;

    public List<Purchase> addPurchase(List<Purchase> purchase) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists {
        repo.add(purchase);
        return purchase;
    }

    public List<Purchase> addPurchaseFromRequest(List<PurchaseRequest> purchaserequest) throws DataBaseWriteException, DataBaseReadException, DBEntryAlreadyExists {
//TODO: transformar em classe de conversão
        List<Purchase> newpurchases = purchaserequest.stream().map(p ->
                new Purchase(0L, 0L, p.getProducts(), new BigDecimal(0))
        ).collect(Collectors.toList());

        repo.add(newpurchases);
        return newpurchases;
    }

    public List<Purchase> findCustomerPurchases(Long customerId) throws NoSuchMethodException, DataBaseReadException {
        return repo.findBy(Map.of("customerId", customerId));
    }

    public BigDecimal effectiveCustomerCart(List<Purchase> purchases) {
        BigDecimal total = BigDecimal.ZERO;

        if (purchases.size() > 0) {
            List<Product> products = purchases.get(0).getProducts();
            total = products.stream()
                    .map(p -> p.getPrice() == null ? BigDecimal.valueOf(0) : p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                    .reduce(BigDecimal.valueOf(0), BigDecimal::add);
        }

        return total;
    }
}
