package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.models.PurchaseRequest;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOperation {

    final private ApplicationRepository<Product, Long> repo;

    public List<Purchase> makePurchase(List<PurchaseRequest> purchaseRequests) {

        List<Purchase> newPurchase = new ArrayList<Purchase>();
        List<Product> findedProducts = new ArrayList<Product>();

        for (PurchaseRequest pRequests : purchaseRequests
        ) {

            //TODO: Make a new error handler
            try {
                findedProducts = findProducts(pRequests.getProducts());

            } catch (Exception e) {
                e.printStackTrace();
            }

            newPurchase.add(
                    new Purchase(null,
                            pRequests.getCustomerId(), // TODO: add existing customer existence validation
                            findedProducts,
                            calcTotalPurchaseValue(findedProducts)
                    ));
        }


        return newPurchase;
    }

    private List<Product> findProducts(List<Product> products) throws Exception {
        List<Product> findedProducts = new ArrayList<Product>();
        for (Product product : products
        ) {
            Optional<Product> searchProduct = repo.find(product.getId());

            //TODO: create class for validation | stock validation must be part of this
            if (searchProduct.isEmpty()) {
                throw new Exception("Product not founded!");
            }

            findedProducts.add(searchProduct.get());

        }

        return findedProducts;
    }

    private BigDecimal calcTotalPurchaseValue(List<Product> products) {

        BigDecimal result = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        return result;

    }
}
