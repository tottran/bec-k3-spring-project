package com.example.demo.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReqResPageDtoOut<T> {
    //    {
    //        "errorCode": int,
    //        "statusCode": int,
    //        "message": string,
    //        "object": {}
    //    }

    @JsonProperty(value="error_code")
    private Integer errorCode;
    @JsonProperty(value="status_code")
    private Integer statusCode;
    private String message;
    private T object;
}
