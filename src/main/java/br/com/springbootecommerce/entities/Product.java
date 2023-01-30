package br.com.springbootecommerce.entities;

import br.com.springbootecommerce.entities.dtos.ProductDataTransfer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_produto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",
            nullable = false)
    private String productName;

    @Column(name = "quantity",
            nullable = false)
    private Integer productQuantity;

    @Column(name = "price",
            nullable = false)
    private BigDecimal productPrice;

    @Column(name = "description",
            nullable = false)
    private String productDescription;

    public Product(ProductDataTransfer data) {
        this.productName = data.name();
        this.productQuantity = data.quantity();
        this.productPrice = data.price();
        this.productDescription = data.description();
    }

    public void update(ProductDataTransfer data) {
        this.productName = data.name();
        this.productQuantity = data.quantity();
        this.productPrice = data.price();
        this.productDescription = data.description();
    }
}
