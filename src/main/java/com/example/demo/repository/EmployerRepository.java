package com.example.demo.repository;

import com.example.demo.entity.Employer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployerRepository extends CrudRepository<Employer, Integer> {
    Optional<Employer> findByEmail(String email);
    Page<Employer> findAll(Pageable page);
}
