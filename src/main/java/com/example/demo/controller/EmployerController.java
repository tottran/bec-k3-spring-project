package com.example.demo.controller;

import com.example.demo.entity.Employer;
import com.example.demo.entity.Product;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.ApiResponsePage;
import com.example.demo.service.EmployerServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/employers",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController {
    EmployerServiceImpl currentServiceImpl = new EmployerServiceImpl();

    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ApiResponsePage<Employer> get(
        @RequestParam(name = "limit", required = false, defaultValue = "5") Integer limit,
        @RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip
    ) {
        return ApiResponsePage.<Employer>from(skip, limit,
        currentServiceImpl.getItemsSize(),
        currentServiceImpl.getItems(limit, skip));
    }
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ApiResponse<Employer> getItemById(@PathVariable("id") Integer id) {
        return ApiResponse.<Employer>success(
            currentServiceImpl.getItemById(id)
        );
    }
    @PostMapping(value = "/add", consumes = MediaType.ALL_VALUE)
    public ApiResponse<Employer> addItem(@RequestBody Employer item) {
        return ApiResponse.<Employer>success(
            currentServiceImpl.addItem(item)
        );
    }
    @PutMapping(value = "/{id}")
    public ApiResponse<Employer> updateItemById(
            @PathVariable("id") Integer id,
            @RequestBody Employer item
    ) {
        return ApiResponse.<Employer>success(
            currentServiceImpl.updateItemById(id, item)
        );
    }
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public void deleteItemById(@PathVariable("id") Integer id) {
        currentServiceImpl.deleteItemById(id);
    }
}
