package br.com.mercadolivre.desafiospring.services;


import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.models.PurchaseRequest;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
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

        List<Purchase> newpurchases = purchaserequest.stream().map(p ->
                new Purchase(0L,0,p.getProducts(),new BigDecimal(0))
        ).collect(Collectors.toList());

        repo.add(newpurchases);
        return newpurchases;
    }

}
