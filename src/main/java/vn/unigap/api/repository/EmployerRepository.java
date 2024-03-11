package vn.unigap.api.repository;

import vn.unigap.api.entity.jpa.Employer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployerRepository extends CrudRepository<Employer, Integer> {
    Optional<Employer> findByEmail(String email);
    Page<Employer> findAll(Pageable page);
}
