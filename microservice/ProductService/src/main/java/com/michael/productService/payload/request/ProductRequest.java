package com.michael.productService.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
    @NotBlank
    private String productName;

    private Long price;

    private Long quantity;
}
