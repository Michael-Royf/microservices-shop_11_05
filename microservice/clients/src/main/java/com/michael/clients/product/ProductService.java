package com.michael.clients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductService {
    @PutMapping("/api/v1/product/reduceQuantity/{productId}")
    void reduceQuantity(@PathVariable(name = "productId") Long productId,
                        @RequestParam(name = "quantity") Long quantity);
}
