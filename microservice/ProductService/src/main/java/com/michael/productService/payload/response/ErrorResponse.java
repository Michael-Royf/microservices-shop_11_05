package com.michael.productService.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss", timezone = "Israel")
    private Date timestamp;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String message;


    public ErrorResponse(int httpStatusCode, HttpStatus httpStatus, String message) {
        timestamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
