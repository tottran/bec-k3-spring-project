package com.example.demo.service;

import com.example.demo.entity.Employer;
import com.example.demo.errorcode.ErrorCode;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public Iterable<Employer> getItems(Integer limit, Integer skip) {
        return employerRepository.findAll();
    }

    @Override
    public Optional<Employer> getItemById(Integer id) {
        return employerRepository.findById(id);
    }

    @Override
    public Employer addItem(Employer item) {
        employerRepository.findByEmail(item.getEmail())
                .ifPresent(employer -> {
                    throw new ApiException(
                            "email already exist",
                            ErrorCode.BAD_REQUEST,
                            HttpStatus.BAD_REQUEST
                    );
                });

        Employer emp = employerRepository.save(item);
        return emp;
    }

    @Override
    public Employer updateItemById(Integer id, Employer updateItem) {
        items.set(id, updateItem);
        return updateItem;
    }

    @Override
    public void deleteItemById(Integer id) {
        items.remove(id.intValue());
    }

    @Override
    public Long getItemsSize() {
        return (long) items.size();
    }
}
