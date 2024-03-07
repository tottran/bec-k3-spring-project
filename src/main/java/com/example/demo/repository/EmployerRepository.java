package com.example.demo.repository;

import com.example.demo.entity.Employer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployerRepository extends CrudRepository<Employer, Integer> {
    Optional<Employer> findByEmail(String email);
}
