package vn.unigap.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.unigap.api.entity.jpa.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<Employer> findByName(String name);
    Optional<Employer> findByEmail(String email);
}