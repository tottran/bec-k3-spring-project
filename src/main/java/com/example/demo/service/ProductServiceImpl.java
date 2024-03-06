package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    public ProductServiceImpl() {
        if(products.isEmpty()) {
            this.initFakeProducts();
        }
    }

    @Override
    public void initFakeProducts() {
        int id;
        for (int i = 0; i < 20; i++) {
            id = i;
            products.add(new Product(id, "code", false));
        }
    }
    @Override
    public List<Product> getProducts(Integer limit, Integer skip) {
        int page = skip / limit + 1;
        int maxIndex = page * limit;
        for (int i = 0; i < products.size(); i++) {
            System.out.println(i + " - " + products.get(i).toString());
        }
        return
            skip > products.size() - 1 ? new ArrayList<>() :
            products.subList(
                skip,
                Math.min(skip + limit, maxIndex)
            );
    }

    @Override
    public Product getProductById(Integer id) {
        return products.get(id);
    }

    @Override
    public Product addProduct(Product product) {
        Product p = new Product(product.getId(), product.getName(), product.getIsDone());
        products.add(p);
        return p;
    }

    @Override
    public Product updateProductById(Integer id, Product newUpdateProduct) {
        return products.set(id, newUpdateProduct);
    }

    @Override
    public void deleteProductById(Integer id) {
        products.remove(id);
    }

    @Override
    public Long getTotalElements() {
        return (long) products.size();
    }
}
