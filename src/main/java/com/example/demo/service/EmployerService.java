package com.example.demo.service;

import com.example.demo.entity.Employer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EmployerService {
    List<Employer> items = new ArrayList<>();
    Iterable<Employer> getItems(Integer limit, Integer skip);
    Optional<Employer> getItemById(Integer id);
    Employer addItem(Employer item);
    Employer updateItemById(Integer id, Employer updateItem);
    void deleteItemById(Integer id);
    Long getItemsSize();
}
