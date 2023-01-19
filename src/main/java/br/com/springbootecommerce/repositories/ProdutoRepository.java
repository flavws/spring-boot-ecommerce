package br.com.springbootecommerce.repositories;

import br.com.springbootecommerce.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeProdutoContaining(String nome);
}
