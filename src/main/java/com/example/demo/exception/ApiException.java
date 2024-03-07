package com.example.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

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
