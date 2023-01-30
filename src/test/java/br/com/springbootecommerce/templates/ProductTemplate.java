package br.com.springbootecommerce.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.springbootecommerce.entities.Product;

import java.math.BigDecimal;

public class ProductTemplate implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Product.class).addTemplate("model", new Rule(){{
            add("id", 1L);
            add("productName", random("Earphone", "Keyboard", "Monitor"));
            add("productQuantity", random(Integer.class, range(1, 500)));
            add("productPrice", new BigDecimal("150"));
            add("productDescription", random("Large", "Small", "Medium"));
        }});
    }
}
