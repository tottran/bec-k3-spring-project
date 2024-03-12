package vn.unigap.api.response;

import vn.unigap.api.errorcode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private Integer errorCode;
    private Integer statusCode;
    private String message;
    private T object;

    public static <T> ApiResponse<T> success(T object, Integer statusCode) {
        return ApiResponse.<T>builder()
                .errorCode(ErrorCode.SUCCESS)
                .statusCode(statusCode)
                .object(object)
                .build();
    }
    public static <T> ApiResponse<T> success(T object) {
        return ApiResponse.<T>builder()
                .errorCode(ErrorCode.SUCCESS)
                .statusCode(HttpStatus.OK.value())
                .object(object)
                .build();
    }
    public static <T> ApiResponse<T> error(Integer errorCode,
                                           Integer httpStatus,
                                           String message) {
        return ApiResponse.<T>builder()
                .errorCode(errorCode)
                .statusCode(httpStatus)
                .message(message)
                .build();
    }
}
