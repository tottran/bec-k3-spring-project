package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ProductService {
    List<Product> products = new ArrayList<>();
    List<Product> getProducts();
    Product getProductById(Integer id);
    void initFakeProducts();
}
