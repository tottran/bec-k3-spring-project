package vn.unigap.api.controller;

import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.common.AbstractResponseController;
import vn.unigap.api.entity.jpa.Employer;
import vn.unigap.api.exception.ApiException;
import vn.unigap.api.response.ApiResponse;
import vn.unigap.api.service.EmployerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/employers",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController extends AbstractResponseController {
    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> get(@Valid PageDtoIn pageDtoIn) {
        try {
            return responseEntity(() -> {
                return this.employerService.getItems(pageDtoIn);
            });
        } catch(ApiException exception) {
            return responseEntity(() -> {
                return exception;
            });
        }
    }
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ApiResponse<?> getItemById(@PathVariable("id") Integer id) {
        try {
            return ApiResponse.<Optional<Employer>>success(
                employerService.getItemById(id)
            );
        } catch(ApiException exception) {
            return ApiResponse.<ApiException>error(
                    exception.getErrorCode(),
                    exception.getHttpStatus(),
                    exception.getMessage()
            );
        }
    }
    @PostMapping(value = "/add", consumes = MediaType.ALL_VALUE)
    public ApiResponse<?> addItem(@RequestBody Employer item) {
        try {
            return ApiResponse.<Employer>success(
                    employerService.addItem(item), HttpStatus.CREATED.value()
            );
        } catch(ApiException exception) {
            return ApiResponse.<ApiException>error(
                    exception.getErrorCode(),
                    exception.getHttpStatus(),
                    exception.getMessage()
            );
        }
    }
    @PutMapping(value = "/{id}")
    public ApiResponse<?> updateItemById(
            @PathVariable("id") Integer id,
            @RequestBody Employer item
    ) {
        try {
            return ApiResponse.<Employer>success(
                    employerService.updateItemById(id, item)
            );
        } catch(ApiException exception) {
            return ApiResponse.<ApiException>error(
                    exception.getErrorCode(),
                    exception.getHttpStatus(),
                    exception.getMessage()
            );
        }
    }
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ApiResponse<?> deleteItemById(@PathVariable("id") Integer id) {
        try {
            employerService.deleteItemById(id);
            return ApiResponse.<String>success(new Object().toString());
        } catch(ApiException exception) {
            return ApiResponse.<ApiException>error(
                    exception.getErrorCode(),
                    exception.getHttpStatus(),
                    exception.getMessage()
            );
        }
    }
}
