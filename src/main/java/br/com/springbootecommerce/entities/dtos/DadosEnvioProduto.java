package br.com.springbootecommerce.entities.dtos;

import java.math.BigDecimal;

public record DadosEnvioProduto(
        String nome,
        Integer qtde,
        BigDecimal valor,
        String descricao
) {
}
