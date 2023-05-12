package com.michael.orderService.config;

import com.michael.orderService.exceptions.payload.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder (){
        return new CustomErrorDecoder();
    }
}
