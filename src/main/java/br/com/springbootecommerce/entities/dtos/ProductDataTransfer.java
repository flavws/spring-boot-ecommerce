package br.com.springbootecommerce.entities.dtos;

import br.com.springbootecommerce.entities.Product;

import java.math.BigDecimal;

public record ProductDataTransfer(
        String name,
        Integer quantity,
        BigDecimal price,
        String description
) {
    public ProductDataTransfer(Product product) {
        this(product.getProductName(), product.getProductQuantity(), product.getProductPrice(), product.getProductDescription());
    }
}
