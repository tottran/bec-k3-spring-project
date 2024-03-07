package com.example.demo.service;

import com.example.demo.entity.Employer;
import com.example.demo.entity.Product;

import java.util.ArrayList;
import java.util.List;

public interface EmployerService {
    List<Employer> items = new ArrayList<>();
    List<Employer> getItems(Integer limit, Integer skip);
    Employer getItemById(Integer id);
    Employer addItem(Employer item);
    Employer updateItemById(Integer id, Employer updateItem);
    void deleteItemById(Integer id);
    void createDummyItems();
    Long getItemsSize();
}
