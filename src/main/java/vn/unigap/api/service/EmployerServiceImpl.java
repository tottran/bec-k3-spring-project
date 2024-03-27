package vn.unigap.api.service;

import vn.unigap.api.common.exception.ApiException;
import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.dto.out.PageDtoOut;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.entity.jpa.Employer;
import vn.unigap.api.entity.jpa.JobProvince;
import vn.unigap.api.errorcode.ErrorCode;
import vn.unigap.api.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.unigap.api.repository.JobProvinceRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployerServiceImpl implements EmployerService {
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobProvinceRepository jobProvinceRepository;

    @Override
    public PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn) {
        Page<Employer> employers = this.employerRepository.findAll(
                PageRequest.of(pageDtoIn.getPage() - 1,
                pageDtoIn.getPageSize(),
                Sort.by("id").ascending()));

        List<EmployerDtoOut> employerDtoOutList = employers.stream()
                .map(employer -> {
                    // Lấy dữ liệu provinceName từ JobProvinceRepository
                    Optional<JobProvince> jobProvince = jobProvinceRepository.findById(employer.getProvince());
                    String provinceName = jobProvince.map(JobProvince::getName).orElse(null);

                    // Tạo đối tượng EmployerDtoOut và điền thông tin vào
                    EmployerDtoOut employerDtoOut = EmployerDtoOut.from(employer);
                    employerDtoOut.setProvinceName(provinceName);

                    return employerDtoOut;
                })
                .toList();

        return PageDtoOut.from(
                pageDtoIn.getPage(),
                pageDtoIn.getPageSize(),
                employers.getTotalElements(), employerDtoOutList);
    }

    @Override
    public EmployerDtoOut get(Long id) {
      Employer employer = employerRepository.findById(id)
          .orElseThrow(() -> new ApiException("Employer not found", ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND));

        // Lấy dữ liệu provinceName từ JobProvinceRepository
        Optional<JobProvince> optionalProvince = jobProvinceRepository.findById(employer.getProvince());
        String provinceName = optionalProvince.map(JobProvince::getName).orElse(null);

        // Tạo đối tượng EmployerDtoOut và điền thông tin vào
        EmployerDtoOut employerDtoOut = EmployerDtoOut.from(employer);
        employerDtoOut.setProvinceName(provinceName);

        return employerDtoOut;
    }

    @Override
    public Optional<Employer> getByName(String name) {
        Employer emp = employerRepository.findByName(name)
                .orElseThrow(() -> new ApiException(
                        "user not found",
                        ErrorCode.NOT_FOUND,
                        HttpStatus.NOT_FOUND));
        return Optional.ofNullable(emp);
    }

    @Override
    public Employer add(Employer item) {
        employerRepository.findByEmail(item.getEmail())
                .ifPresent(employer -> {
                    throw new ApiException(
                            "email already exist",
                            ErrorCode.BAD_REQUEST,
                            HttpStatus.BAD_REQUEST
                    );
                });
        jobProvinceRepository.findById(item.getProvince())
                .orElseThrow(() -> new ApiException(
                        "provinceId is not exist",
                        ErrorCode.NOT_FOUND,
                        HttpStatus.NOT_FOUND
                ));

        Employer emp = employerRepository.save(item);
        return emp;
    }

    @Override
    public Employer update(Long id, Employer updateItem) {
        Employer emp = employerRepository.findById(id)
            .orElseThrow(() -> new ApiException(
                    "user not found",
                    ErrorCode.NOT_FOUND,
                    HttpStatus.NOT_FOUND));

        if(updateItem.getName()!=null)
            emp.setName(updateItem.getName());
        if(updateItem.getEmail()!=null)
            emp.setEmail(updateItem.getEmail());
        if(updateItem.getProvince()!=null)
            emp.setProvince(updateItem.getProvince());
        if(updateItem.getDescription()!=null)
            emp.setDescription(updateItem.getDescription());
        emp.setUpdatedAt(new Date());
        return emp;
    }

    @Override
    public void delete(Long id) {
        Employer emp = employerRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        "user not found",
                        ErrorCode.NOT_FOUND,
                        HttpStatus.NOT_FOUND));
        if(emp!=null)
            employerRepository.delete(emp);
    }
}
