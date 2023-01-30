package br.com.springbootecommerce.service;

import br.com.springbootecommerce.entities.Product;
import br.com.springbootecommerce.entities.dtos.ProductDataTransfer;
import br.com.springbootecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product getProductById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public List<Product> getProductsByName(String name){
        return repository.findByProductNameContaining(name);
    }

    public Product createProduct(ProductDataTransfer data){
        return repository.save(new Product(data));
    }

    public Product updateProduct(Long id, ProductDataTransfer data){
        var product = repository.findById(id).orElseThrow();
        product.update(data);
        return product = repository.save(product);
    }

    public void deleteProduct(Long id){
            repository.deleteById(id);
    }

}
