package br.com.mercadolivre.desafiospring.validators;

import br.com.mercadolivre.desafiospring.exceptions.db.DataBaseReadException;
import br.com.mercadolivre.desafiospring.models.Product;
import br.com.mercadolivre.desafiospring.models.Purchase;
import br.com.mercadolivre.desafiospring.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PurchaseValidator {

    public static List<String> StockValidation(ApplicationRepository<Product, Long> productRepo, List<Purchase> purchases) throws NoSuchMethodException, DataBaseReadException {

        List<String> errors = new ArrayList<>();

        for (Purchase purchase:
             purchases) {

            for (Product product :
                    purchase.getProducts()) {

                Product stockProduct = productRepo.findBy(
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
}
