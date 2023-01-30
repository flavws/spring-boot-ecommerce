package br.com.springbootecommerce.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.springbootecommerce.entities.Product;
import br.com.springbootecommerce.entities.dtos.ProductDataTransfer;
import br.com.springbootecommerce.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Before
    public void before(){
        FixtureFactoryLoader.loadTemplates("br.com.springbootecommerce.templates");
    }

    @Mock //mocka o objeto e o instancia quando a aplicação for rodada
    ProductRepository productRepository;

    @InjectMocks // injetando uma classe
    private ProductService service;

    @BeforeEach
    public void start() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProductsShouldReturnAListOfProducts(){
        var productList = List.of(new Product());
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        var response = service.getAllProducts();

        assertNotNull(response);
        assertEquals(response, productList);
    }

    @Test
    public void createProductShouldReturnAProduct(){
        ProductDataTransfer data = new ProductDataTransfer("Earphone", 1, new BigDecimal(90.00), "Too loud");
        var product = new Product();

        Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);

        var response = service.createProduct(data);

        assertEquals(product, response);
    }
    @Test
    public void getProductByIdShouldReturnASpecificProductWhenIdExists(){
        var id = 1L;
        Product product = Fixture.from(Product.class).gimme("model");
        var request = Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

        var response = service.getProductById(id);
        assertEquals(product, response);
    }

    @Test
    public void getProductByNameShouldReturnSpecificProductWhenNameExists(){

        List<Product> product = Fixture.from(Product.class).gimme(5,"model");
        var name = product.get(1).getProductName();
        var request = Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);
        Mockito.when(productRepository.findByProductNameContaining(name)).thenReturn(product);

        var response = service.getProductsByName(name);
        assertEquals(product, response);
    }

    @Test
    public void updateProductShouldReturnAUpdatedProductWhenIdExists(){
        var id = 1L;
        Product product = Fixture.from(Product.class).gimme("model");
        ProductDataTransfer data = new ProductDataTransfer(product);

        var request = Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

        var response = service.updateProduct(id, data);
        assertEquals(product, response);
    }

    @Test
    public void deleteProductShouldVerifyIfProductHasBeenDeletedWhenIdExists(){

        var id = 1L;
        Product product = Fixture.from(Product.class).gimme("model");

        var request = Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

        service.deleteProduct(id);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(id);

    }



}
