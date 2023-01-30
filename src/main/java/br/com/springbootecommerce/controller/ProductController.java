package br.com.springbootecommerce.controller;

import br.com.springbootecommerce.entities.Product;
import br.com.springbootecommerce.entities.dtos.ProductDataTransfer;
import br.com.springbootecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/ecommerce")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAllProdutos(){
        return ResponseEntity.ok().body(service.getAllProducts());
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Product> getProdutoById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.getProductById(id));
    }

    @GetMapping("/product/")
    public ResponseEntity<List<Product>> getProdutoByName(@RequestParam(value="name") String nome){
        return ResponseEntity.ok().body(service.getProductsByName(nome));
    }

    @PostMapping(value = "/products")
    public ResponseEntity createProduto(@RequestBody @Valid ProductDataTransfer dados){
        var produto = service.createProduct(dados);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<Product> updateProduto(@PathVariable Long id, @RequestBody @Valid ProductDataTransfer data){
        return ResponseEntity.ok().body(service.updateProduct(id, data));
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity deleteProduto(@PathVariable Long id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
