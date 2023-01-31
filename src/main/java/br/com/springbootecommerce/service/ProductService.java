package br.com.springbootecommerce.service;

import br.com.springbootecommerce.entities.Product;
import br.com.springbootecommerce.entities.dtos.ProductDataTransfer;
import br.com.springbootecommerce.exceptions.DatabaseException;
import br.com.springbootecommerce.exceptions.ResourceNotFoundException;
import br.com.springbootecommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    }

    public List<Product> getProductsByName(String name){
        return repository.findByProductNameContaining(name);
    }

    public Product createProduct(ProductDataTransfer data){
        return repository.save(new Product(data));
    }

    public Product updateProduct(Long id, ProductDataTransfer data){
        try {
            var product = repository.findById(id).orElseThrow();
            product.update(data);
            return product = repository.save(product);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("ID not found " + id);
        }

    }

    public void deleteProduct(Long id){
        try {
            repository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("ID not found " + id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

}
