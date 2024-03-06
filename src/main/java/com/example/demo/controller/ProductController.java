package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.ApiResponsePage;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    ProductServiceImpl productService = new ProductServiceImpl();

    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ApiResponsePage<Product> getProducts(
        @RequestParam(name = "limit", required = false, defaultValue = "5") Integer limit,
        @RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip
    ) {
        return ApiResponsePage.<Product>from(skip, limit,
                productService.getTotalElements(),
                productService.getProducts(limit, skip));
    }
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ApiResponse<Product> getProductById(@PathVariable("id") Integer id) {
        return ApiResponse.<Product>success(productService.getProductById(id));
    }
    @PostMapping("/add")
    public ApiResponse<Product> addProduct(@RequestBody Product product) {
        return ApiResponse.<Product>success(productService.addProduct(product));
    }
    @PutMapping("/{id}")
    public ApiResponse<Product> updateProductById(
        @PathVariable("id") Integer id,
        @RequestBody Product product
    ) {
        return ApiResponse.<Product>success(
            productService.updateProductById(id, product));
    }
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public void deleteProductById(@PathVariable("id") Integer id) {
        productService.deleteProductById(id);
    }
}
