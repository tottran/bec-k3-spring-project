package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class MainController {
    ProductServiceImpl productService = new ProductServiceImpl();

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }
}
