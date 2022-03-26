package br.com.mercadolivre.desafiospring.validators;

import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.exceptions.validations.OutOfStockException;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PurchaseValidator {

    public void StockValidation(ApplicationRepository<Product, Long> productRepo, List<Purchase> newPurchase) throws OutOfStockException, NoSuchMethodException, DataBaseReadException {

        for (Purchase purchase :
                newPurchase) {
            for (Product product :
                    purchase.getProducts()) {

                Product stockProduct = productRepo.findBy(
                        Map.of("name", product.getName(), "brand", product.getBrand())).get(0);

                if (stockProduct.getQuantity() < product.getQuantity()) {
                    throw new OutOfStockException("Sem estoque: Há apenas "
                            .concat(stockProduct.getQuantity().toString())
                            .concat(" unidades do produto ")
                            .concat(product.getName())
                            .concat(" em estoque. O pedido foi de ")
                            .concat(product.getQuantity().toString())
                            .concat("."));
                }
            }
        }
    }
}
