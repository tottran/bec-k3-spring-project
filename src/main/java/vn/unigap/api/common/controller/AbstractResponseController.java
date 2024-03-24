package vn.unigap.api.common.controller;

import vn.unigap.api.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractResponseController {
    public <T> ResponseEntity<ApiResponse<T>> responseEntity(CallbackFunction<T> callback) {
        return responseEntity(callback, HttpStatus.OK);
    }

    public <T> ResponseEntity<ApiResponse<T>> responseEntity(CallbackFunction<T> callback, HttpStatus status) {
      T result = callback.execute();
      return ResponseEntity.status(status).body(ApiResponse.success(result));
    }
}
