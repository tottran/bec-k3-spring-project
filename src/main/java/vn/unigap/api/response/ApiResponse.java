package vn.unigap.api.response;

import vn.unigap.api.errorcode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private Integer errorCode;
    private HttpStatusCode statusCode;
    private String message;
    private T object;

    public static <T> ApiResponse<T> success(T object) {
      return ApiResponse.<T>builder()
          .errorCode(ErrorCode.SUCCESS)
          .statusCode(HttpStatus.OK)
          .message("Successfully")
          .object(object)
          .build();
    }
    public static <T> ApiResponse<T> success(T object, String message) {
      return ApiResponse.<T>builder()
          .errorCode(ErrorCode.SUCCESS)
          .statusCode(HttpStatus.OK)
          .message(message)
          .object(object)
          .build();
    }
    public static <T> ApiResponse<T> success(T object, HttpStatus statusCode) {
      return ApiResponse.<T>builder()
              .errorCode(ErrorCode.SUCCESS)
              .statusCode(statusCode)
              .message("Successfully")
              .object(object)
              .build();
  }
    public static <T> ApiResponse<T> error(Integer errorCode,
                                           HttpStatus httpStatus,
                                           String message) {
        return ApiResponse.<T>builder()
                .errorCode(errorCode)
                .statusCode(httpStatus)
                .message(message)
                .build();
    }
}
