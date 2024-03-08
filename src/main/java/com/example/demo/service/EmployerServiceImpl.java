package com.example.demo.service;

import com.example.demo.dto.in.PageDtoIn;
import com.example.demo.dto.out.EmployerDtoOut;
import com.example.demo.entity.Employer;
import com.example.demo.errorcode.ErrorCode;
import com.example.demo.exception.ApiException;
import com.example.demo.repository.EmployerRepository;
import com.example.demo.response.ApiResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public ApiResponsePage<EmployerDtoOut> getItems(PageDtoIn pageDtoIn) {
        Page<Employer> employers = this.employerRepository.findAll(
                PageRequest.of(pageDtoIn.getPage() - 1,
                pageDtoIn.getPageSize(),
                Sort.by("id").ascending()));
        return ApiResponsePage.from(
                pageDtoIn.getPage(),
                pageDtoIn.getPageSize(),
                employers.getTotalElements(),
                employers.stream()
                .map(EmployerDtoOut::from)
                .toList());
    }

    @Override
    public Optional<Employer> getItemById(Integer id) {
        Employer emp = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        "user not found",
                        ErrorCode.NOT_FOUND,
                        HttpStatus.NOT_FOUND));
        return Optional.ofNullable(emp);
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
        Employer emp = employerRepository.findById(id)
            .orElseThrow(() -> new ApiException(
                    "user not found",
                    ErrorCode.NOT_FOUND,
                    HttpStatus.NOT_FOUND));
        emp.setName(updateItem.getName());
        emp.setEmail(updateItem.getEmail());
        emp.setProvince(updateItem.getProvince());
        emp.setDescription(updateItem.getDescription());
        return emp;
    }

    @Override
    public void deleteItemById(Integer id) {
        Employer emp = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        "user not found",
                        ErrorCode.NOT_FOUND,
                        HttpStatus.NOT_FOUND));
        employerRepository.delete(emp);
    }
}
