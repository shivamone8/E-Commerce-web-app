package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void addProduct(Product product ){
        productRepository.save(product);
    }

    public void removeProductById(int product_id ){
        productRepository.deleteById(product_id);
    }
    public Optional<Product> getProductById(int product_id){
        return productRepository.findById(product_id);
    }
    public List<Product> getAllProductsByCategoryId(int id){
        return productRepository.findAllByCategory_id(id);
    }

}
