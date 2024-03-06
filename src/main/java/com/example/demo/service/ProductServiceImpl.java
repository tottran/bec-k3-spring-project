package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    public ProductServiceImpl() {
        if(products.size() <= 0) {
            this.initFakeProducts();
        }
    }

    @Override
    public void initFakeProducts() {
        Integer id;
        for (int i = 0; i <= 1; i++) {
            id = i;
            products.add(new Product(id, "code", false));
        }
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product getProductById(Integer id) {
        return products.get(id);
    }
}
