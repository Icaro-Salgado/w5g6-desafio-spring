package br.com.mercadolivre.desafiospring.services;


import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.models.PurchaseRequest;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    final private ApplicationRepository<Purchase, Long> repo;

    public List<Purchase> addPurchase(List<Purchase> purchase) throws IOException {
        repo.add(purchase);
        return purchase;
    }

    public List<Purchase> addPurchaseFromRequest(List<PurchaseRequest> purchaserequest) throws IOException {
//TODO: transformar em classe de conversão
        List<Purchase> newpurchases = purchaserequest.stream().map(p ->
                new Purchase(0L,0,p.getProducts(),new BigDecimal(0))
        ).collect(Collectors.toList());

        repo.add(newpurchases);
        return newpurchases;
    }

    public List<Purchase> findCustomerPurchases(long customerId) throws IOException, NoSuchMethodException {
        return repo.findBy(Map.of("customerId", customerId));
    }

    public BigDecimal effectiveCustomerCart(List<Purchase> purchases) throws IOException, NoSuchMethodException {
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
