package app.server.product.impl;

import app.server.product.ProductRepository;
import framework.annotations.spring.Autowired;
import framework.annotations.spring.Qualifier;
import framework.annotations.spring.Service;

@Service
public class ProductService {

    @Qualifier("productRepository")
    @Autowired(verbose = true)
    private ProductRepository repository;

    public String getProducts() {
        return repository.get();
    }
}
