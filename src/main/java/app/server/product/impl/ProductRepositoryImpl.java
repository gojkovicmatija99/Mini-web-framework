package app.server.product.impl;

import app.server.product.ProductRepository;
import framework.annotations.spring.Component;
import framework.annotations.spring.Qualifier;

@Qualifier("productRepository")
@Component
public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public String get() {
        System.out.println("[SERVER] SELECT * FROM Products");
        return "Samsung TV - 400$";
    }
}
