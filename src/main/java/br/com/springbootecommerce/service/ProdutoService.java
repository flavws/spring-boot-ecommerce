package br.com.springbootecommerce.service;

import br.com.springbootecommerce.entities.Produto;
import br.com.springbootecommerce.entities.dtos.DadosEnvioProduto;
import br.com.springbootecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public List<Produto> getAllProdutos(){
        return repository.findAll();
    }

    public Produto getProdutoById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public List<Produto> getProdutoByNome(String nome){
        return repository.findByNomeProdutoContaining(nome);
    }

    public Produto createProduto(DadosEnvioProduto dados){
        return repository.save(new Produto(dados));
    }

    public Produto updateProduto(Long id, DadosEnvioProduto dados){
        var produto = repository.findById(id).orElseThrow();
        produto.update(dados);
        return repository.save(produto);
    }

    public Boolean deleteProduto(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
