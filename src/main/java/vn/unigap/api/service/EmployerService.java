package vn.unigap.api.service;

import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.entity.jpa.Employer;

import java.util.Optional;

public interface EmployerService {
  PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn);
  EmployerDtoOut get(Long id);
  Optional<Employer> getByName(String name);
  Employer add(Employer item);
  Employer update(Long id, Employer updateItem);
  void delete(Long id);
}
