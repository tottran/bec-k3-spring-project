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
        int totalElements = products.size();
        int remain = totalElements % limit;
        boolean isLastPage = page == (totalElements / limit + remain);
        int maxIndex = (page * (remain > 0 ? (limit - 1) : limit)) + remain;
        return
            skip > products.size() - 1 ? new ArrayList<>() :
            products.subList(
                skip,
                Math.min(
                        skip + (isLastPage ? ((limit - 1) + remain): limit),
                        maxIndex)
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
        Product oldProduct = products.get(id);
        if(newUpdateProduct.getId()==null) newUpdateProduct.setId(oldProduct.getId());
        if(newUpdateProduct.getName()==null) newUpdateProduct.setName(oldProduct.getName());
        if(newUpdateProduct.getIsDone()==null) newUpdateProduct.setIsDone(oldProduct.getIsDone());
        products.set(id, newUpdateProduct);
        return newUpdateProduct;
    }

    @Override
    public void deleteProductById(Integer id) {
        products.remove(id.intValue());
    }

    @Override
    public Long getTotalElements() {
        return (long) products.size();
    }
}
