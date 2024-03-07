package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ProductService {
    List<Product> products = new ArrayList<>();
    List<Product> getProducts(Integer limit, Integer skip);
    Product getProductById(Integer id);
    Product addProduct(Product product);
    Product updateProductById(Integer id, Product newUpdateProduct);
    void deleteProductById(Integer id);
    void initFakeProducts();
    Long getTotalElements();
}
