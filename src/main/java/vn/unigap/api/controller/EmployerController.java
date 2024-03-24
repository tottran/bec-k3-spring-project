package vn.unigap.api.controller;

import vn.unigap.api.dto.in.PageDtoIn;
import vn.unigap.api.common.controller.AbstractResponseController;
import vn.unigap.api.common.exception.ApiException;
import vn.unigap.api.entity.jpa.Employer;
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
    @Autowired
    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> get(@Valid PageDtoIn pageDtoIn) {
        try {
            return responseEntity(() -> {
                return this.employerService.list(pageDtoIn);
            });
        } catch(ApiException exception) {
            return responseEntity(() -> {
                return exception;
            });
        }
    }
    
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
      return responseEntity(() -> {
        return this.employerService.get(id);
      });
    }

    @GetMapping(value = "/name", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getEmployerByName(
            @RequestParam("name") String name
    ) {
        try {
            Optional<Employer> employer = employerService.getByName(name);
            System.out.println(" ***************" + name);
            if (employer != null) {
                return ResponseEntity.ok().body(employer);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Error error) {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping(value = "/add", consumes = MediaType.ALL_VALUE)
    public ApiResponse<?> add(@RequestBody Employer item) {
      try {
        return ApiResponse.<Employer>success(
            employerService.add(item), HttpStatus.CREATED);
      } catch (ApiException exception) {
        return ApiResponse.<ApiException>error(
            exception.getErrorCode(),
            exception.getHttpStatus(),
            exception.getMessage());
      }
    }
    @PutMapping(value = "/{id}")
    public ApiResponse<?> update(
            @PathVariable("id") Long id,
            @RequestBody Employer item
    ) {
        try {
            return ApiResponse.<Employer>success(
                    employerService.update(id, item)
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
    public ApiResponse<?> delete(@PathVariable("id") Long id) {
        try {
            employerService.delete(id);
            return ApiResponse.<String>success("{}");
        } catch(ApiException exception) {
            return ApiResponse.<ApiException>error(
                    exception.getErrorCode(),
                    exception.getHttpStatus(),
                    exception.getMessage()
            );
        }
    }
}
