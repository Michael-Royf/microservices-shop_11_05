package com.michael.orderService.exceptions.payload.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael.orderService.exceptions.payload.CustomException;
import com.michael.orderService.payload.response.ErrorResponseForOpenFeign;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());


        try {
            ErrorResponseForOpenFeign errorResponse
                    = objectMapper.readValue(response.body().asInputStream(),
                    ErrorResponseForOpenFeign.class);

            return new CustomException(errorResponse.getErrorMessage(),
                    errorResponse.getErrorCode(),
                    response.status());

        } catch (IOException e) {
            throw new CustomException("Internal Server Error",
                    "INTERNAL_SERVER_ERROR",
                    500);
        }

    }

}
