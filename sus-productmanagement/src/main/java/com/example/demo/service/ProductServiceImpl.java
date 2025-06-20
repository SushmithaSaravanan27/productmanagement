package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existing = getProductById(id);
        existing.setPname(product.getPname());
        existing.setPrice(product.getPrice());
        existing.setReview(product.getReview());
        return repository.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        Product existing = getProductById(id); // ensure product exists
        repository.delete(existing);
    }
}
