package vn.unigap.api.service;

import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.entity.Employer;

import java.util.Optional;

public interface EmployerService {
    PageDtoOut<EmployerDtoOut> getItems(PageDtoIn pageDtoIn);
    Optional<Employer> getItemById(Integer id);
    Employer addItem(Employer item);
    Employer updateItemById(Integer id, Employer updateItem);
    void deleteItemById(Integer id);
}
