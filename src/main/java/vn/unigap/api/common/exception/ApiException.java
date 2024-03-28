package vn.unigap.api.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {
    private Integer errorCode;
    private HttpStatus httpStatus;

    public ApiException(String message, Integer errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
