package br.com.springbootecommerce.entities;

import br.com.springbootecommerce.entities.dtos.DadosEnvioProduto;
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
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",
            nullable = false)
    private String nomeProduto;

    @Column(name = "qtde",
            nullable = false)
    private Integer qtdeProduto;

    @Column(name = "valor",
            nullable = false)
    private BigDecimal valorProduto;

    @Column(name = "descricao",
            nullable = false)
    private String descricaoProduto;

    public Produto(DadosEnvioProduto dados) {
        this.nomeProduto = dados.nome();
        this.qtdeProduto = dados.qtde();
        this.valorProduto = dados.valor();
        this.descricaoProduto = dados.descricao();
    }

    public void update(DadosEnvioProduto dados) {
        this.nomeProduto = dados.nome();
        this.qtdeProduto = dados.qtde();
        this.valorProduto = dados.valor();
        this.descricaoProduto = dados.descricao();
    }
}
