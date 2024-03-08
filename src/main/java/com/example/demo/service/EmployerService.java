package com.example.demo.service;

import com.example.demo.dto.in.PageDtoIn;
import com.example.demo.dto.out.EmployerDtoOut;
import com.example.demo.entity.Employer;
import com.example.demo.response.ApiResponsePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EmployerService {
    ApiResponsePage<EmployerDtoOut> getItems(PageDtoIn pageDtoIn);
    Optional<Employer> getItemById(Integer id);
    Employer addItem(Employer item);
    Employer updateItemById(Integer id, Employer updateItem);
    void deleteItemById(Integer id);
}
