package br.com.springbootecommerce.controller;

import br.com.springbootecommerce.entities.Produto;
import br.com.springbootecommerce.entities.dtos.DadosEnvioProduto;
import br.com.springbootecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/ecommerce")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/produtos")
    public ResponseEntity<List<Produto>> getAllProdutos(){
        return ResponseEntity.ok().body(service.getAllProdutos());
    }

    @GetMapping(value = "/produto/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.getProdutoById(id));
    }

    @GetMapping("/produto/")
    public ResponseEntity<List<Produto>> getProdutoByName(@RequestParam(value="nome") String nome){
        return ResponseEntity.ok().body(service.getProdutoByNome(nome));
    }

    @PostMapping(value = "/produtos")
    public ResponseEntity createProduto(@RequestBody @Valid DadosEnvioProduto dados){
        var produto = service.createProduto(dados);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping(value = "/produto/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody @Valid DadosEnvioProduto dados){
        return ResponseEntity.ok().body(service.updateProduto(id, dados));
    }

    @DeleteMapping(value = "/produto/{id}")
    public ResponseEntity deleteProduto(@PathVariable Long id){
        if(service.deleteProduto(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
