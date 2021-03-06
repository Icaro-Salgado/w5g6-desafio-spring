package br.com.mercadolivre.desafiospring.services;

import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseWriteException;
import br.com.mercadolivre.desafiospring.exceptions.validations.OutOfStockException;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.models.PurchaseRequest;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseOperation {

    final private ApplicationRepository<Product, Long> repo;

    private List<String> StockValidation(List<PurchaseRequest> purchases) throws DataBaseReadException {

        List<String> errors =  new ArrayList<>();

        for (PurchaseRequest purchaseRequest:
                purchases) {

            for (Product product :
                    purchaseRequest.getProducts()) {

                Product stockProduct = repo.findBy(
                        Map.of("name", product.getName(), "brand", product.getBrand())).get(0);

                if (stockProduct.getQuantity() < product.getQuantity()) {
                    errors.add("Sem estoque: Há apenas "
                            .concat(stockProduct.getQuantity().toString())
                            .concat(" unidades do produto ")
                            .concat(product.getName())
                            .concat(" em estoque. O pedido foi de ")
                            .concat(product.getQuantity().toString())
                            .concat("."));
                }
            }
        }
        return errors;
    }

    public List<Purchase> makePurchase(List<PurchaseRequest> purchaseRequests) throws OutOfStockException, DataBaseReadException, DataBaseWriteException {

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

            List<String> outOfStockErrors = StockValidation(purchaseRequests);

            if (!outOfStockErrors.isEmpty()) {
                throw new OutOfStockException(String.join("\n", outOfStockErrors));
            }

            for (Product p : findedProducts) {
                Optional<Product> requestProduct = pRequests.getProducts()
                        .stream().filter(r -> r.getId().equals(p.getId()))
                        .findFirst();

                if(requestProduct.isEmpty()){
                    continue;
                }


                repo.update(Map.of("id", p.getId()), Map.of("quantity", p.getQuantity() - requestProduct.get().getQuantity()));
            }

            newPurchase.add(
                    new Purchase(null,
                            pRequests.getCustomerId(), // TODO: add existing customer existence validation
                            findedProducts,
                            calcTotalPurchaseValue(findedProducts, pRequests.getProducts())
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

    private BigDecimal calcTotalPurchaseValue(List<Product> products, List<Product> purchaseRequestProducts) {

        return products.stream().map(p -> {
            Optional<Product> requestProduct = purchaseRequestProducts.stream().filter(requestP -> requestP.getId().equals(p.getId())).findFirst();
            if(requestProduct.isEmpty()){
                return BigDecimal.ZERO;
            }
            return p.getPrice().multiply(new BigDecimal(requestProduct.get().getQuantity()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
